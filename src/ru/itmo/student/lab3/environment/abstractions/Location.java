package ru.itmo.student.lab3.environment.abstractions;

import ru.itmo.student.lab3.environment.Furniture;
import ru.itmo.student.lab3.people.Person;

import javax.sound.sampled.Line;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Location {
    public Type getType() {
        return type;
    }

    public enum Type { Default, Ceremony}

    public Location(Type t) {
        type = t;
    }

    private LinkedList<Person> characters = new LinkedList<>();
    private LinkedList<Person> charactersCenter = new LinkedList<>();;
    private LinkedList<Person> charactersSide = new LinkedList<>();
    private final Type type;
    protected LinkedList<Furniture> objects;

    private boolean isPresent(Person p) {
        var allCharacters = new LinkedList<Person>();
        allCharacters.addAll(characters);
        allCharacters.addAll(charactersCenter);
        allCharacters.addAll(charactersSide);
        return allCharacters.contains(p);
    }

    private boolean isPresent(Person p, Position pos) {
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
        if (!isPresent(p)) characters.add(p);
    }
    public void addCharacter(Person p, Position pos) {
       if (!isPresent(p)) {
           switch (pos) {
               case Unspecified -> characters.add(p);
               case Center      -> charactersCenter.add(p);
               case Side        -> charactersSide.add(p);
           }
       }
    }

    public void removeCharacter(Person p) {
        characters.remove(p);
        charactersSide.remove(p);
        charactersCenter.remove(p);
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