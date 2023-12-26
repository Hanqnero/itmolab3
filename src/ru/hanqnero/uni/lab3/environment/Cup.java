package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.environment.food.Food;

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

    private void setTemp(Temperature t) {
        this.temp = t;
    }
}