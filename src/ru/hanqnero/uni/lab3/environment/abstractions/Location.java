package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.environment.Furniture;
import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.WrongToolException;
import ru.hanqnero.uni.lab3.people.Person;
import ru.hanqnero.uni.lab3.people.interfaces.HasExhaustion;

import java.util.ArrayList;
import java.util.List;

public class Location {

    public static class Ground {
        public enum SoilType {NORMAL, ROCKY}
        private final SoilType type;

        private static final int MIN_TOUGHNESS = 0;
        private static final int MAX_TOUGHNESS = 100;
        private final int soilToughness;

        public enum Tools {HANDS,SPOON,SHOVEL,PICKAXE}
        private final Tools defaultTool;

        public Ground(int toughness, SoilType type) {
            soilToughness = Math.max(MIN_TOUGHNESS, Math.min(MAX_TOUGHNESS, toughness));
            this.type = type;
            defaultTool =
                soilToughness < 20 ? Tools.HANDS :
                soilToughness < 40 ? Tools.SPOON :
                soilToughness < 80 ? Tools.SHOVEL:
                                     Tools.PICKAXE;
        }

        float chanceToFindRock() {
            if (type != SoilType.ROCKY) return 0f;
            return (float) soilToughness / 75 * 100;
        }

        private boolean caughtRock = false;

        private static final double ROCK_DIGGING_TIME_MULTIPLIER = 1.5d;
        private long getDiggingTime() {
            double time = soilToughness * 100L;
            if (caughtRock) time *= ROCK_DIGGING_TIME_MULTIPLIER;
            return (long) time;
        }

        public int getSoilToughness() {
            return soilToughness;
        }

        public void whenDug(Tools usedTool, Person digger) throws WrongToolException {
            Tools tool = defaultTool;
            if (caughtRock)
                tool = Tools.PICKAXE;

            if (usedTool.compareTo(defaultTool) != 0)
                throw new WrongToolException(usedTool, tool);

            var diggingTime = getDiggingTime();
            if (digger instanceof HasExhaustion p) {
                diggingTime += p.getExhaustion();
                p.setExhaustion(p.getExhaustion() + soilToughness / 10);
            }

            try {
                Thread.sleep(diggingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            caughtRock = Math.random() > chanceToFindRock();
        }
    }
    @SuppressWarnings("unused")
    public Type getType() {
        return type;
    }

    public enum Type { DEFAULT, CEREMONY, DIGGING_SPOT}

    public Location(Type t) {
        type = t;
    }

    private final List<Person> characters = new ArrayList<>();
    private final List<Person> charactersCenter = new ArrayList<>();
    private final List<Person> charactersSide = new ArrayList<>();
    private final Type type;
    protected List<Furniture> objects = new ArrayList<>();

    public List<Furniture> getObjects() {
        return new ArrayList<>(objects);
    }

    private boolean isPresent(Person p) {
        var allCharacters = new ArrayList<Person>();
        allCharacters.addAll(characters);
        allCharacters.addAll(charactersCenter);
        allCharacters.addAll(charactersSide);
        return allCharacters.contains(p);
    }

    public List<Person> getCharacters() {
        var allCharacters = new ArrayList<Person>();
        allCharacters.addAll(characters);
        allCharacters.addAll(charactersCenter);
        allCharacters.addAll(charactersSide);
        return allCharacters;
    }

    @SuppressWarnings("unused")
    private boolean isPresent(Person p, Position pos) {
        switch (pos) {
            case UNSPECIFIED -> {
                return characters.contains(p);
            }
            case CENTER -> {
                return charactersCenter.contains(p);
            }
            case SIDE -> {
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
        }

    }
    public void addCharacter(Person p, Position pos) {
       if (!isPresent(p)) {
           switch (pos) {
               case UNSPECIFIED -> characters.add(p);
               case CENTER      -> charactersCenter.add(p);
               case SIDE        -> charactersSide.add(p);
           }
           p.setCurrentLocation(this);
       }
    }

    public void removeCharacter(Person p) {
        characters.remove(p);
        charactersSide.remove(p);
        charactersCenter.remove(p);
    }

    public void addObject(Furniture f) { this.objects.add(f); }
    @SuppressWarnings("unused")
    public void removeObject(Furniture f) { this.objects.remove(f); }

    public enum Position {
        UNSPECIFIED, CENTER, SIDE
    }

    public void changePosition(Person p, Position pos) {
        if (isPresent(p)) {
            removeCharacter(p);
            addCharacter(p, pos);
        }
    }
}