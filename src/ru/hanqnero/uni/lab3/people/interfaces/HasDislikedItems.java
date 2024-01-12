package ru.hanqnero.uni.lab3.people.interfaces;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface HasDislikedItems {
    List<Object> getDislikedItems();
    void dislikeNewItem(Object o);
    void stopDisliking(Object o);
}
