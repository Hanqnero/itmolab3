package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.Person;

import java.util.LinkedList;
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

    private final LinkedList<Person> characters = new LinkedList<>();

    public void addCharacter(Person p) {
        if (getLocation() == null) throw new RuntimeException("Can not add character to scene that does not have location");
        this.getLocation().addCharacter(p);
        p.setCurrentScene(this);
        if (!characters.contains(p)) characters.add(p);
    }

    public void removeCharacter(Person p) {
        if (getLocation() != null) getLocation().removeCharacter(p);
        p.setCurrentScene(null);
        characters.remove(p);
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
