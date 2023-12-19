package ru.itmo.student.lab3.environment;

public class Stove {

    private Temperature temp;

    public void turnOff() {
        this.temp = Temperature.Cold;
    }
    public void turnOn() {
        this.temp = Temperature.Hot;
    }

    public void heat(Food food) {
        if (food.getCookedDegree() == Food.CookedDegree.Burned) return;
        var currentCookedDegree = food.getCookedDegree().ordinal();
        var newCookedDegree = Food.CookedDegree.values()[currentCookedDegree+1];

        food.setCookedDegree(newCookedDegree);
    }
}
