package ru.hanqnero.uni.lab3.environment.abstractions;

public class Preparations extends Location{
    public Preparations(Type t) {
        super(t);
    }

    private float completeness = 0f;

    public float getCompleteness() {
        return completeness;
    }

    public void workOnCompletion(float amount) {
        if (!isCompleted()) {
            completeness += amount;

            var MAX = 1f; // Clamp between logically right numbers
            var MIN = 0f;
            completeness = Math.max(Math.min(completeness, MAX), MIN);
        }
    }

    public boolean isCompleted() {
        return completeness == 1f;
    }
}