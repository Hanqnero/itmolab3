package ru.itmo.student.lab3.people;

import ru.itmo.student.lab3.environment.abstractions.Question;

public interface CanBeAskedQuestions {
    void whenAskedQuestion(Question q);
    double getAnsweringSpeed();
}
