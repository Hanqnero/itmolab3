package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.Person;

import java.util.LinkedList;

public class Location {
    public enum Type { Default, }

    public Location(Type t) { this.type = t; }

    protected LinkedList<Person> characters = new LinkedList<Person>();
    protected final Type type;
    protected LinkedList<Furniture> objects;

    public void addCharacter(Person p) { this.characters.add(p); }
    public void removeCharacter(Person p) { this.characters.remove(p); }
}
