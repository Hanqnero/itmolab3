package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.Person;

public interface CanBeHeld {
    void whenHeld(Person holder);
    Person getHolder();
}
