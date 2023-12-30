package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.Person;
import ru.hanqnero.uni.lab3.environment.Furniture;

import java.util.LinkedList;
import java.util.UUID;

public class Location implements HasUUID {
    public Type getType() {
        return type;
    }

    private final UUID id = UUID.randomUUID();

    @Override
    public String getID() {
        return id.toString();
    }

    public enum Type { Default, Ceremony }

    public Location(Type t) {
        type = t;
    }

    private LinkedList<Person> characters = new LinkedList<>();
    private LinkedList<Person> charactersCenter = new LinkedList<>();;
    private LinkedList<Person> charactersSide = new LinkedList<>();
    private final Type type;
    protected LinkedList<Furniture> objects = new LinkedList<>();

    public LinkedList<Furniture> getObjects() {
        return new LinkedList<>(objects);
    }

    public boolean isPresent(Person p) {
        var allCharacters = new LinkedList<Person>();
        allCharacters.addAll(characters);
        allCharacters.addAll(charactersCenter);
        allCharacters.addAll(charactersSide);
        return allCharacters.contains(p);
    }

    public LinkedList<Person> getCharacters() {
        var allCharacters = new LinkedList<Person>();
        allCharacters.addAll(characters);
        allCharacters.addAll(charactersCenter);
        allCharacters.addAll(charactersSide);
        return allCharacters;
    }

    public boolean isPresent(Person p, Position pos) {
        switch (pos) {
            case Unspecified -> {
                return characters.contains(p);
            }
            case Center -> {
                return charactersCenter.contains(p);
            }
            case Side -> {
                return charactersSide.contains(p);
            }
            default -> {
                assert true: "Should not be reached";
                return false;
            }
        }
    }

    public void addCharacter(Person p) {
        if (!isPresent(p)) {
            characters.add(p);
            p.setCurrentLocation(this);
//          Flaw in previous implementation could lead to situation where person's saved scene is not related to location,
//          Which contradicts author's new view of the model and object hierarchy.
            p.setCurrentScene(null);
        }

    }
    public void addCharacter(Person p, Position pos) {
       if (!isPresent(p)) {
           switch (pos) {
               case Unspecified -> characters.add(p);
               case Center      -> charactersCenter.add(p);
               case Side        -> charactersSide.add(p);
           }
           p.setCurrentLocation(this);
           p.setCurrentScene(null); // See line 76
       }
    }

    public void removeCharacter(Person p) {
        characters.remove(p);
        charactersSide.remove(p);
        charactersCenter.remove(p);
        p.setCurrentScene(null); // See line 76
        p.setCurrentLocation(null);
    }

    public void addObject(Furniture f) { this.objects.add(f); }
    public void removeObject(Furniture f) { this.objects.remove(f); }

    public enum Position {
        Unspecified, Center, Side
    }

    public void changePosition(Person p, Position pos) {
        if (isPresent(p)) {
            removeCharacter(p);
            addCharacter(p, pos);
        }
    }
}