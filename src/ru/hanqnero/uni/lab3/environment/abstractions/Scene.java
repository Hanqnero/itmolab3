package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.Person;

import java.util.UUID;

public class Scene implements HasUUID {
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
        p.setCurrentScene(this);
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

    private final UUID id = UUID.randomUUID();
    @Override
    public String getID() {
        return id.toString();
    }
}
