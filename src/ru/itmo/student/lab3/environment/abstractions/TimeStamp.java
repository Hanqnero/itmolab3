package ru.itmo.student.lab3.environment.abstractions;

import java.time.Instant;

public record TimeStamp(Type type, Instant date, SpecialType specialType) {
    public enum Type {
        Special, Regular,
    }
    public enum SpecialType {
        None, EllieBirthday, Funeral,
    }
}
