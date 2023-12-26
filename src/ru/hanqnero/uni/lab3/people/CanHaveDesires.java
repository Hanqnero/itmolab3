package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Desire;

public interface CanHaveDesires {
    public void desire(Desire d);
    public Desire getCurrentDesire();
}
