package ru.hanqnero.uni.lab3.environment.riding;

import ru.hanqnero.uni.lab3.people.interfaces.CanRide;

public interface CanBeRidden {
    void whenRidden(CanRide rider);

    void whenStoppedRiding(CanRide rider);
}
