package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;

public interface AskToChangePosition {
    void whenAskedToChangePosition(Location.Position pos);
}
