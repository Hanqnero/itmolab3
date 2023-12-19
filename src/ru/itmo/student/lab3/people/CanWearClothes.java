package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.Clothes;

import java.util.LinkedList;

public interface CanWearClothes {
    public LinkedList<Clothes> getCurrentClothes();
    public void setCurrentClothes(LinkedList<Clothes> c);

}
