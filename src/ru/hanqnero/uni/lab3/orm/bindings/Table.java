package ru.hanqnero.uni.lab3.orm.bindings;

import ru.hanqnero.uni.lab3.people.ActionMood;

import java.util.LinkedList;

public enum Table {



    PEOPLE("people"),
    MOODS("moods"),
    CLOTHES("clothes"),
    FOOD("food"),
    LOCATION("locations"),
    SCENES("scenes");

    private final String table;

    Table(String t) {
        table = t;
    }
    @Override
    public String toString() {
        return table;
    }



}
