package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.environment.abstractions.Scene;
import ru.itmo.student.lab3.people.Person;

public class Photo implements CanBeHeld {
    private final Type type;
    private final Scene picturedScene;
    private Person holder;

    public Photo(Scene s, Type t) {
        picturedScene = s;
        type = t;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void whenHeld(Person holder) {
       this.holder = holder;
    }

    @Override
    public Person getHolder() {
        return this.holder;
    }

    public Scene getPicturedScene() { return picturedScene; }


    public enum Type {
        Polaroid, Digital
    }
}
