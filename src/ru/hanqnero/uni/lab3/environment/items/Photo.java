package ru.hanqnero.uni.lab3.environment.items;

import ru.hanqnero.uni.lab3.environment.abstractions.Scene;
import ru.hanqnero.uni.lab3.environment.items.interfaces.CanBeHeld;
import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;

public class Photo implements CanBeHeld {
    private final Type type;
    private final Scene picturedScene;
    private CanHoldItems holder;

    public Photo(Scene s, Type t) {
        picturedScene = s;
        type = t;
    }

    @SuppressWarnings("unused")
    public Type getType() {
        return type;
    }

    @Override
    public void whenHeld(CanHoldItems holder) {
        this.holder = holder;
    }

    @Override
    public CanHoldItems getHolder() {
        return this.holder;
    }

    @Override
    public void whenDropped() {
        holder = null;
    }

    @SuppressWarnings("unused")
    public Scene getPicturedScene() {
        return picturedScene;
    }


    public enum Type {
        Polaroid, Digital
    }
}
