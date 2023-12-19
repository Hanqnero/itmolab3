package ru.itmo.student.lab3.environment;

public class Clothes {
    public Color getColor() {
        return color;
    }
    public enum Type {
        Suit,Blouse,Jeans,
    }
    private final Color color;
    public Clothes(Color c) {
        this.color = c;
    }
}
