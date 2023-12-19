package ru.itmo.student.lab3.environment;

public class Medicine {
    public final int hpChange;

    public enum Type { Pill, Injection, Default }

    private final Type type;

    public Medicine(Type t, int h) { this.type = t; this.hpChange = h; }

    public Type getType() { return type; }

}
