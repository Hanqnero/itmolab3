package ru.hanqnero.uni.lab3.environment.abstractions.exceptions;

public class NoLocationException extends RuntimeException {
    public NoLocationException() {
        super("Scene does not have location, " +
                "so the scene location cannot be used nor scene can contain characters");
    }
}
