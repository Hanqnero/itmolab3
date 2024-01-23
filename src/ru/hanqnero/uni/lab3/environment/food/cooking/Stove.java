package ru.hanqnero.uni.lab3.environment.food.cooking;

import ru.hanqnero.uni.lab3.environment.food.CookedDegree;
import ru.hanqnero.uni.lab3.environment.food.FoodToCook;
import ru.hanqnero.uni.lab3.environment.items.interfaces.Furniture;
import ru.hanqnero.uni.lab3.environment.properties.Temperature;

public class Stove implements Furniture {

    @SuppressWarnings("unused")
    private Temperature temp;

    public void turnOff() {
        this.temp = Temperature.Cold;
    }

    public void turnOn() {
        this.temp = Temperature.Hot;
    }

    public void heat(FoodToCook food) {
        if (food.getCookedDegree() == CookedDegree.Burned) return;
        var currentCookedDegree = food.getCookedDegree().ordinal();
        var newCookedDegree = CookedDegree.values()[currentCookedDegree + 1];

        food.setCookedDegree(newCookedDegree);
    }
}
