package ru.hanqnero.uni.lab3.environment;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;

import java.util.UUID;

public class Clothes implements HasUUID {
    private final UUID id = UUID.randomUUID();

    public Color getColor() {
        return this.color;
    }

    public ClothingType getType() {
        return this.type;
    }

    @Override
    public String getID() {
        return id.toString();
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
