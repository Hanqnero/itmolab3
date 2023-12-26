package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.people.CanRide;

public interface CanBeRidden {
    void whenRidden(CanRide rider);
    void whenStoppedRiding(CanRide rider);
}
