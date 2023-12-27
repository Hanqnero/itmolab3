package ru.hanqnero.uni.lab3.environment.food;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;

import java.util.UUID;

public abstract class Food implements HasUUID {

    private final UUID id = UUID.randomUUID();

    public String getID() {
        return id.toString();
    }

    private final int saturationChange;
    private final Taste taste;
    private boolean consumed = false;

    protected Food(int saturationChange, Taste taste) {
        this.saturationChange = saturationChange;
        this.taste = taste;
    }

    public int getSaturationChange() { return saturationChange; }
    public boolean isConsumed() { return consumed; }
    public void consume() { this.consumed = true; }

    public Taste getTaste() { return taste; }
}
