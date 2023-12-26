package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.CanBeRidden;

public interface CanRide {
    void ride(CanBeRidden obj);
    void stopRiding();
    boolean isRiding();
}
