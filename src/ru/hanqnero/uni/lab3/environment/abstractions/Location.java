package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.environment.Furniture;
import ru.hanqnero.uni.lab3.environment.Tool;
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
        private Tools requiredToolType;

        public Ground(int toughness, SoilType type) {
            soilToughness = Math.max(MIN_TOUGHNESS, Math.min(MAX_TOUGHNESS, toughness));
            this.type = type;
            updateRequiredTool();
        }

        float chanceToFindRock() {
            if (type != SoilType.ROCKY) return 0f;
            return (float) soilToughness / 5 * 3 / 100;
        }

        public enum RockType {NONE, NORMAL, TOO_HARD}
        private RockType caughtRock = RockType.NONE;

        private void updateRequiredTool() {
            if (caughtRock.compareTo(RockType.NONE) > 0) {
                requiredToolType = Tools.PICKAXE;
                return;
            }
            var toughness = getSoilToughness();
            requiredToolType =
                toughness < 20 ? Tools.HANDS :
                toughness < 40 ? Tools.SPOON :
                toughness < 80 ? Tools.SHOVEL:
                                 Tools.PICKAXE;
        }

        private static final double ROCK_DIGGING_TIME_MULTIPLIER = 1.5d;
        private long getDiggingTime() {
            double time = soilToughness * 10L;
            if (caughtRock.compareTo(RockType.NONE) > 0) time *= ROCK_DIGGING_TIME_MULTIPLIER;
            return (long) time;
        }

        public int getSoilToughness() {
            return soilToughness;
        }

        boolean checkIfCanUseTool(Tool tool) {
            var toolType = tool.getType();
            if (requiredToolType.equals(Tools.PICKAXE))
                return toolType.equals(Tools.PICKAXE);

            return toolType.compareTo(requiredToolType) >= 0 && !toolType.equals(Tools.PICKAXE);
        }

        private void diggingAction(long diggingTime) {
            try {
                Thread.sleep(diggingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void updateAfterSuccessfulDig() {
            var r = Math.random();
            boolean rockOnNextDig = r > chanceToFindRock();
            if (rockOnNextDig)
                caughtRock = Math.random() > 0.5f ? RockType.NORMAL : RockType.TOO_HARD;
            else caughtRock = RockType.NONE;
            updateRequiredTool();
        }

        public RockType whenDug(Tool tool, Person digger) throws WrongToolException {
            if (!checkIfCanUseTool(tool))
                throw new WrongToolException(tool.getType(), requiredToolType);

            var diggingTime = getDiggingTime();
            if (digger instanceof HasExhaustion d)
                diggingTime += d.getExhaustion();

            diggingAction(diggingTime);
            var dugRockType = caughtRock;
            updateAfterSuccessfulDig();
            return dugRockType;
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
    protected final List<Furniture> objects = new ArrayList<>();

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