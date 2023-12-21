package ru.itmo.student.lab3.environment.food;

public abstract class Food {

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
