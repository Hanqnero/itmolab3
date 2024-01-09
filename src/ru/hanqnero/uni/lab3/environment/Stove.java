package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.environment.food.CookedDegree;
import ru.hanqnero.uni.lab3.environment.food.FoodToCook;

public class Stove implements Furniture {

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
        var newCookedDegree = CookedDegree.values()[currentCookedDegree+1];

        food.setCookedDegree(newCookedDegree);
    }
}
