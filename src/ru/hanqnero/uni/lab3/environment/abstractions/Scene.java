package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.environment.abstractions.exceptions.NoLocationException;
import ru.hanqnero.uni.lab3.people.Person;

public class Scene {
    private Location location;
    private TimeStamp timestamp;
    public Location getLocation() throws NoLocationException {
        if (location == null) throw new NoLocationException();
        return location;
    }
    public void setLocation(Location l) {
        location = l;
    }

    public void addCharacter(Person p) throws NoLocationException {
        if (location == null) throw new NoLocationException();
        this.getLocation().addCharacter(p);
        p.setCurrentScene(this);
    }
    @SuppressWarnings("unused")
    public void removeCharacter(Person p) throws NoLocationException{
        if (location == null) throw new NoLocationException();
        location.removeCharacter(p);
    }

    public void setTimestamp(TimeStamp ts) {
        timestamp = ts;
    }

    public TimeStamp getTimestamp() {
        return timestamp;
    }
}
