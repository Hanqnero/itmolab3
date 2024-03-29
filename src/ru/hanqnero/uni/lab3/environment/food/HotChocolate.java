package ru.hanqnero.uni.lab3.environment.food;

import ru.hanqnero.uni.lab3.environment.properties.Temperature;

public class HotChocolate extends FoodToCook implements HasTemperature {
    private Temperature temp;

    public HotChocolate(int saturationChange, Taste taste, Temperature t) {
        super(saturationChange, taste);
        this.temp = t;
    }

    @Override
    public Temperature getTemp() {
        return this.temp;
    }

    @Override
    public void setTemp(Temperature t) {
        this.temp = t;
    }

    @Override
    public String toString() {
        return "Hot chocolate: Temp: %s, saturation: %d, is consumed: %b".formatted(
                temp, getSaturationChange(), isConsumed()
        );
    }
}
