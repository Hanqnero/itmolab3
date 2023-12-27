package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.ORM.Entity;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.environment.abstractions.Location;

import java.util.HashSet;
import java.util.HexFormat;
import java.util.Set;
import java.util.UUID;

@Entity(tableName="people", primaryKey = "id")
public abstract class Person {

    private final UUID id = UUID.randomUUID();
    protected final String name;
    public String getName() { return name; };

    private Scene currentScene;
    private Location currentLocation;
    private HashSet<ActionMood> currentMoods = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void goOutOfLocation() { this.currentLocation.removeCharacter(this); }

    public Set<ActionMood> getCurrentMoods() { return currentMoods; }

//    public void setCurrentMoods(ActionMood currentMoods) { this.currentMoods = currentMoods; }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public void setCurrentScene(Scene currentScene) {

        this.currentScene = currentScene;
    }

    public void doMoodAction(ActionMood mood) {
        switch (mood) {
            case Screaming -> System.out.println(name + "Screams");
        }
    }

    public void addNewMood(ActionMood mood) {
        currentMoods.add(mood);
    }
    public void removeMood(ActionMood mood) {
        currentMoods.remove(mood);
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getId() {
        return id.toString();
    }
}
