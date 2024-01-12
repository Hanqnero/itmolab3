package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.people.Person;

public class Scene {
    private Location location;
    private TimeStamp timestamp;
    public Location getLocation() {
        return this.location;
    }
    public void setLocation(Location l) {
        this.location = l;
    }

    public void addCharacter(Person p) {    // TODO
        this.getLocation().addCharacter(p);
        p.setCurrentScene(this);
    }
    @SuppressWarnings("unused")
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
