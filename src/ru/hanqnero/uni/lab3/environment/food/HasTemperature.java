package ru.hanqnero.uni.lab3.environment.food;

import ru.hanqnero.uni.lab3.environment.properties.Temperature;

public interface HasTemperature {
    Temperature getTemp();
    void setTemp(Temperature t);
}
