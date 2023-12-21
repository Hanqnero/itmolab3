package ru.itmo.student.lab3.people;

import java.util.LinkedList;
import java.util.Objects;

public interface HasDislikedItems {
    LinkedList<Object> getDislikedItems();
    void dislikeNewItem(Object o);
    void stopDisliking(Object o);
}
