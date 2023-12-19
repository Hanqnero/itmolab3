package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.Person;

public class Photo {
    public enum Type { Polaroid, Digital, Other, }
    private final Person picturedPerson;

    public Photo(Person p) { this.picturedPerson = p; }

    public Person getPicturedPerson() { return picturedPerson; }

}
