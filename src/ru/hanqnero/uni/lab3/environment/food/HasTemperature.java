package ru.hanqnero.uni.lab3.environment.food;

import ru.hanqnero.uni.lab3.environment.properties.Temperature;

public interface HasTemperature {
    @SuppressWarnings("unused")
    Temperature getTemp();

    @SuppressWarnings("unused")
    void setTemp(Temperature t);
}
