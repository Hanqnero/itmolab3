package ru.itmo.student.lab3.environment;

public abstract class Container<T> {
    protected T content;

    public abstract boolean fill(T o);

}
