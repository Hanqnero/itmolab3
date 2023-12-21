package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.Clothes;

import java.util.LinkedList;

public interface CanWearClothes {
    LinkedList<Clothes> getCurrentClothes();
    void setCurrentClothes(LinkedList<Clothes> c);
    void addClothingItem(Clothes item);
    void removeClothingItem(Clothes item);

}
