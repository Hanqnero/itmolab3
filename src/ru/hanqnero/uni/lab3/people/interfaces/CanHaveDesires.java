package ru.hanqnero.uni.lab3.people.interfaces;

public interface CanHaveDesires {

    record Desire(DesireType type, Object object) {}
    enum DesireType { Drink, Sleep, None, }

    @SuppressWarnings("unused")
    void desire(Desire d);
    @SuppressWarnings("unused")
    Desire getCurrentDesire();
}
