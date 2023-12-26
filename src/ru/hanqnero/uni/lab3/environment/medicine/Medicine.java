package ru.hanqnero.uni.lab3.environment.medicine;

public abstract class Medicine {
    private final int hpChange;

    protected Medicine(int hpChange) {
        this.hpChange = hpChange;
    }

    public int getHpChange() {
        return this.hpChange;
    }
}
