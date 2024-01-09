package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.TV;

public interface CanWatchTV extends HasMedicalCondition {
    default void watchTV(TV tv) {
        tv.whenWatched(this);
    }
}
