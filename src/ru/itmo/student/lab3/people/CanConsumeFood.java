package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.Container;
import ru.itmo.student.lab3.environment.food.Food;

public interface CanConsumeFood {
    void consumeFood(Food f);
    void consumeFood(Container<Food> c);
    int getSaturation();
    void setSaturation(int s);
}
