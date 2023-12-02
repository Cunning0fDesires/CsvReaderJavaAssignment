package org.example.exceptioins;

public class MissingInputDataException extends RuntimeException {
    public MissingInputDataException(String message) {
        super(message);
    }
}
