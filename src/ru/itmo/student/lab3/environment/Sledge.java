package ru.itmo.student.lab3.environment;

import ru.itmo.student.lab3.people.CanRide;
import ru.itmo.student.lab3.people.Person;

import java.util.LinkedList;

public class Sledge implements CanBeRidden {
    private LinkedList<CanRide> riddenBy;
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
