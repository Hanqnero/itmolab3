package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.Clothes;

import java.util.List;

public interface CanWearClothes {
    @SuppressWarnings("unused")
    List<Clothes> getCurrentClothes();
    @SuppressWarnings("unused")
    void setCurrentClothes(List<Clothes> c);
    void addClothingItem(Clothes item);
    @SuppressWarnings("unused")
    void removeClothingItem(Clothes item);

}
