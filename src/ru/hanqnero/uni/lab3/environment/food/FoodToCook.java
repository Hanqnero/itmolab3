package ru.hanqnero.uni.lab3.environment.food;

public abstract class FoodToCook extends Food {
    private CookedDegree cookedDegree = CookedDegree.Raw;

    protected FoodToCook(int saturationChange, Taste taste) {
        super(saturationChange, taste);
    }

    public CookedDegree getCookedDegree() {
        return cookedDegree;
    }

    public void setCookedDegree(CookedDegree cookedDegree) {
        this.cookedDegree = cookedDegree;
    }
}
