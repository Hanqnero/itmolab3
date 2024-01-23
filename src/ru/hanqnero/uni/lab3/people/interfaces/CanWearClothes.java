package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.items.Clothes;

import java.util.Set;

public interface CanWearClothes {
    @SuppressWarnings("unused")
    Set<Clothes> getCurrentClothes();

    @SuppressWarnings("unused")
    void setCurrentClothes(Set<Clothes> c);

    void addClothingItem(Clothes item);

    @SuppressWarnings("unused")
    void removeClothingItem(Clothes item);

}
