package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.CanRide;

import java.util.LinkedList;
import java.util.UUID;

public class Sledge implements CanBeRidden, HasUUID {
    private final UUID id = UUID.randomUUID();
    private LinkedList<CanRide> riddenBy = new LinkedList<>();

    @Override
    public void whenRidden(CanRide rider) {
        if(!(riddenBy.contains(rider))) {
            riddenBy.add(rider);
        }
    }

    @Override
    public void whenStoppedRiding(CanRide rider) {
        riddenBy.remove(rider);
    }

    @Override
    public String getID() {
        return id.toString();
    }
}
