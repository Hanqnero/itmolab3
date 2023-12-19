package ru.itmo.student.lab3.environment;

public class Cup extends Container<Food> implements Furniture {

    protected Temperature temp;

    @Override
    public boolean fill(Food f) {
        this.content = f;
        this.setTemp(f.getTemp());
        return true; // Can not fail
    }

    private void setTemp(Temperature t) {
        this.temp = t;
    }
}