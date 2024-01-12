package ru.hanqnero.uni.lab3.environment.medicine;

import ru.hanqnero.uni.lab3.environment.properties.Color;

public class TakenInternallyMedicine extends Medicine{
    private final Color color;
    public TakenInternallyMedicine(int hpChange, Color color) {
        super(hpChange);
        this.color = color;
    }
}
