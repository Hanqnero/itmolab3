package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.CanRide;

public interface CanBeRidden extends HasUUID {
    void whenRidden(CanRide rider);
    void whenStoppedRiding(CanRide rider);
}
