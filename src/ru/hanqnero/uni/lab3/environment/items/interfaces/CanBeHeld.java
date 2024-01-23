package ru.hanqnero.uni.lab3.environment.items.interfaces;

import ru.hanqnero.uni.lab3.people.interfaces.CanHoldItems;

public interface CanBeHeld {
    void whenHeld(CanHoldItems holder);

    @SuppressWarnings("unused")
    CanHoldItems getHolder();

    void whenDropped();
}
