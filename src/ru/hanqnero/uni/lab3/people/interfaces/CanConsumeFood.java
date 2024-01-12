package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.Container;
import ru.hanqnero.uni.lab3.environment.food.Food;

public interface CanConsumeFood {
    void consumeFood(Food f);
    void consumeFood(Container<Food> c);
    int getSaturation();
    void setSaturation(int s);
}
