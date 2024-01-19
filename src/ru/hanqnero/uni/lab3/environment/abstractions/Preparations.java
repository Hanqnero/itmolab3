package ru.hanqnero.uni.lab3.environment.abstractions;

public class Preparations extends Location{

    private float completeness = 0f;

    public float getCompleteness() {
        return completeness;
    }

    void setCompleteness(float completeness) {
        this.completeness = completeness;
        this.completeness = Math.min(Math.max(this.completeness, 0f), 1f);
    }

    public boolean isCompleted() {
        return completeness == 1f;
    }

    public Preparations(Type t) {
        super(t);
    }

    public void workOnCompletion(float amount) {
        setCompleteness(completeness + amount);
    }
}
