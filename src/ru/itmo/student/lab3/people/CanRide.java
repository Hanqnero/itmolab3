package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.CanBeRidden;

public interface CanRide {
    void ride(CanBeRidden obj);
    void stopRiding();
    boolean isRiding();
}
