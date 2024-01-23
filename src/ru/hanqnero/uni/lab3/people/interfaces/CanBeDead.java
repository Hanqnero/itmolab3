package ru.hanqnero.uni.lab3.people.interfaces;


public interface CanBeDead {
    void die();
    @SuppressWarnings("unused")
    void resurrect();
    @SuppressWarnings("unused")
    boolean getAlive();
}
