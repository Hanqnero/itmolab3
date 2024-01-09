package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;

import java.util.HashSet;
import java.util.Set;

public abstract class Person {
    private Scene currentScene;
    private Location currentLocation;
    private final HashSet<ActionMood> currentMoods = new HashSet<>();

    public Person() {}

    public void goOutOfLocation() { this.currentLocation.removeCharacter(this); }

    public Set<ActionMood> getCurrentMoods() { return currentMoods; }

//    public void setCurrentMoods(ActionMood currentMoods) { this.currentMoods = currentMoods; }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public void setCurrentScene(Scene currentScene) {

        this.currentScene = currentScene;
    }

    private float speechLoudness = 100f;
    public float getSpeechLoudness() {return speechLoudness;}
    public void doMoodAction(ActionMood mood) {
        switch (mood) {
            case Screaming -> {
                this.speechLoudness = 420f;
                System.out.println(getName() + "Screams");
            }
        }
    }

    public void addNewMood(ActionMood mood) {
        currentMoods.add(mood);
    }
    public void removeMood(ActionMood mood) {

        switch (mood) {
            case Screaming -> {
                this.speechLoudness = 100f;
                currentMoods.remove(ActionMood.Screaming);
            }
        }
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public abstract String getName();
}
