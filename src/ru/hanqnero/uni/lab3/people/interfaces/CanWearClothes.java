package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.Clothes;

import java.util.LinkedList;
import java.util.List;

public interface CanWearClothes {
    List<Clothes> getCurrentClothes();
    void setCurrentClothes(List<Clothes> c);
    void addClothingItem(Clothes item);
    void removeClothingItem(Clothes item);

}
