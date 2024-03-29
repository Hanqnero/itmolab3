package ru.hanqnero.uni.lab3.environment.items.containers;

public abstract class Container<T> {
    protected T content; // TODO: Make this an Option<T> type
    private boolean empty = true;

    public boolean isEmpty() {
        return this.empty;
    }

    void setEmpty(boolean e) {
        this.empty = e;
    }

    public abstract void fill(T o);

    public abstract void empty();

    public abstract T getContent();

}
