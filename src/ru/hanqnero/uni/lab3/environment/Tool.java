package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.environment.abstractions.Location;

public interface Tool {
    Location.Ground.Tools getType();
}
