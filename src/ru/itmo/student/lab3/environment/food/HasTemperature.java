package ru.itmo.student.lab3.environment.food;

import ru.itmo.student.lab3.environment.Temperature;

public interface HasTemperature {
    Temperature getTemp();
    void setTemp(Temperature t);
}
