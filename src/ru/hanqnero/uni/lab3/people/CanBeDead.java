package ru.hanqnero.uni.lab3.people;

public interface CanBeDead {
    void die();
    void resurrect();
    LifeStatus getLifeStatus();
}
