package ru.hanqnero.uni.lab3.people.interfaces;

import ru.hanqnero.uni.lab3.environment.items.TV;

public interface CanWatchTV extends HasMedicalCondition {
    default void watchTV(TV tv) {
        tv.whenWatched(this);
    }
}
