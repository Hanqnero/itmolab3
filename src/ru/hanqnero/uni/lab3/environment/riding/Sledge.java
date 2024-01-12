package ru.hanqnero.uni.lab3.environment.riding;

import ru.hanqnero.uni.lab3.environment.riding.CanBeRidden;
import ru.hanqnero.uni.lab3.people.interfaces.CanRide;

import java.util.LinkedList;
import java.util.List;

public class Sledge implements CanBeRidden {
    private final List<CanRide> riddenBy = new LinkedList<>();
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
