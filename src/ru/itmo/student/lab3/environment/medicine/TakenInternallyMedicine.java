package ru.itmo.student.lab3.environment.medicine;

import ru.itmo.student.lab3.environment.Color;

public class TakenInternallyMedicine extends Medicine{
    private final Color color;
    public TakenInternallyMedicine(int hpChange, Color color) {
        super(hpChange);
        this.color = color;
    }
}
