package ru.hanqnero.uni.lab3.people.interfaces;

import java.util.Set;

@SuppressWarnings("unused")
public interface HasDislikedItems {
    Set<Object> getDislikedItems();
    void dislikeNewItem(Object o);
    void stopDisliking(Object o);
}
