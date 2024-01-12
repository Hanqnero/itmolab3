package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.riding.CanBeRidden;

public interface CanRide {
    void ride(CanBeRidden obj);
    @SuppressWarnings("unused")
    void stopRiding();
    boolean isRiding();
}
