package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.people.CanRide;

import java.util.LinkedList;

public class Sledge implements CanBeRidden {
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
}
