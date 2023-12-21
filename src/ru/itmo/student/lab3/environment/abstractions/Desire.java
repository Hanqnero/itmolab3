package ru.itmo.student.lab3.environment.abstractions;

import ru.itmo.student.lab3.people.DesireType;

public record Desire(DesireType type, Object object) {}
