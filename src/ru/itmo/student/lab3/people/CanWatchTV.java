package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.TV;

public interface CanWatchTV extends HasMedicalCondition {
    default void watchTV(TV tv) {
        tv.whenWatched(this);
    }
}
