package ru.hanqnero.uni.lab3.environment.items.containers;

import ru.hanqnero.uni.lab3.environment.food.Food;
import ru.hanqnero.uni.lab3.environment.properties.Temperature;

public class Cup extends Container<Food> {

    protected Temperature temp;

    @Override
    public void fill(Food f) {
        if (this.isEmpty()) {
            this.content = f;
            this.setEmpty(false);
        }
    }

    @Override
    public void empty() {
        this.content = null;
        this.setEmpty(true);
    }

    @Override
    public Food getContent() {
        return this.content;
    }

    @SuppressWarnings("unused")
    private void setTemp(Temperature t) {
        this.temp = t;
    }
}