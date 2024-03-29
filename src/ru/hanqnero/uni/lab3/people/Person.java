package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;
import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.people.moods.ActionMood;

import java.util.HashSet;
import java.util.Set;

public abstract class Person {
    private final HashSet<ActionMood> currentMoods = new HashSet<>();
    private Scene currentScene;
    private Location currentLocation;
    private float speechLoudness = 100f;

    public void goOutOfLocation() {
        this.currentLocation.removeCharacter(this);
    }

    public Set<ActionMood> getCurrentMoods() {
        return currentMoods;
    }

    public Scene getCurrentScene() {
        return this.currentScene;
    }

    public void setCurrentScene(Scene currentScene) {

        this.currentScene = currentScene;
    }

    @SuppressWarnings("unused")
    public float getSpeechLoudness() {
        return speechLoudness;
    }

    public void doMoodAction(ActionMood mood) {
        //noinspection SwitchStatementWithTooFewBranches
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

        //noinspection SwitchStatementWithTooFewBranches
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
