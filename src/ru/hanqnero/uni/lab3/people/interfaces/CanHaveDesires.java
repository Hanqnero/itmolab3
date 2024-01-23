package ru.hanqnero.uni.lab3.people.interfaces;

public interface CanHaveDesires {

    @SuppressWarnings("unused")
    void desire(Desire d);

    @SuppressWarnings("unused")
    Desire getCurrentDesire();

    enum DesireType {Drink, Sleep, None,}

    record Desire(DesireType type, Object object) {
    }
}
