package ru.itmo.student.lab3.people;

public interface CanBeDead {
    void die();
    void resurrect();
    LifeStatus getLifeStatus();
}
