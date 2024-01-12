package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.environment.properties.Color;

public class Clothes {
    public Color getColor() {
        return this.color;
    }

    public ClothingType getType() {
        return this.type;
    }

    public enum ClothingType {
        Suit, Blouse, Jeans, Jacket
    }
    private final Color color;
    private final ClothingType type;

    public Clothes(Color c, ClothingType t) {
        this.color = c;
        this.type = t;
    }

    public boolean equals(Object that) {
        if (!(that instanceof Clothes)) {
            return false;
        }
        var areTypesSame =  ((Clothes) that).getType() == this.getType();
        var areColorsSame = ((Clothes) that).getColor() == this.getColor();
        return areTypesSame && areColorsSame;
    }
}
