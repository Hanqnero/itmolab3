package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.CanRide;
import ru.itmo.student.lab3.people.Person;

public interface CanBeRidden {
    void whenRidden(CanRide rider);
    void whenStoppedRiding(CanRide rider);
}
