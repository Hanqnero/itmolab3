package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.abstractions.Desire;

public interface CanHaveDesires {
    public void desire(Desire d);
    public Desire getCurrentDesire();
}
