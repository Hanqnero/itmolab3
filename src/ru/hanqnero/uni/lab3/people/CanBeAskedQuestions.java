package ru.hanqnero.uni.lab3.people;

import ru.hanqnero.uni.lab3.environment.abstractions.Question;

public interface CanBeAskedQuestions {
    void whenAskedQuestion(Question q);
    double getAnsweringSpeed();
}
