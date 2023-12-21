package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.abstractions.Scene;
import ru.itmo.student.lab3.environment.abstractions.Location;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public abstract class Person {
    protected final String name;

    private Scene currentScene;
    private Location currentLocation;
    private Location.Position locationPosition = Location.Position.Unspecified;
    private Set<ActionMood> currentMoods;

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
}
