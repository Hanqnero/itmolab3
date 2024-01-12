package ru.hanqnero.uni.lab3.people.interfaces;

import java.util.List;

@SuppressWarnings("unused")
public interface HasDislikedItems {
    List<Object> getDislikedItems();
    void dislikeNewItem(Object o);
    void stopDisliking(Object o);
}
