package ru.hanqnero.uni.lab3.environment.abstractions.exceptions;

public class NoToolException extends RuntimeException{
    public NoToolException() {
        super("Tried to dig without proper tool.");
    }
}
