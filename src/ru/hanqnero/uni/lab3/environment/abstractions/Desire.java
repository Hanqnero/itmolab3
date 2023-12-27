package ru.hanqnero.uni.lab3.environment.abstractions;

import ru.hanqnero.uni.lab3.orm.bindings.HasUUID;
import ru.hanqnero.uni.lab3.people.DesireType;

public record Desire(DesireType type, HasUUID object) {}
