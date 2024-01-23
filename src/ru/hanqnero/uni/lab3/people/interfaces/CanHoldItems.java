package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.items.interfaces.CanBeHeld;

public interface CanHoldItems {
    CanBeHeld getItemHeld();

    void pickUp(CanBeHeld item);

    void drop();
}
