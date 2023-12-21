package ru.itmo.student.lab3.environment.abstractions;

import ru.itmo.student.lab3.people.Person;
import java.util.LinkedList;

public class Scene {
    private Location location;
    private TimeStamp timestamp;
    public Location getLocation() {
        return this.location;
    }
    public void setLocation(Location l) {
        this.location = l;
    }

    public void addCharacter(Person p) {
        this.getLocation().addCharacter(p);
    }
    public void removeCharacter(Person p) {
        this.getLocation().removeCharacter(p);
    }

    public void setTimestamp(TimeStamp ts) {
        timestamp = ts;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }
}
