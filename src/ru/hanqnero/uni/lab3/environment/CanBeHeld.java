package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.people.Person;

public interface CanBeHeld {
    void whenHeld(Person holder);
    Person getHolder();
}
