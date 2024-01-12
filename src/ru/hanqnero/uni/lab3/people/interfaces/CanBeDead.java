package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.people.properties.LifeStatus;

public interface CanBeDead {
    void die();
    @SuppressWarnings("unused")
    void resurrect();
    @SuppressWarnings("unused")
    LifeStatus getLifeStatus();
}
