package com.gschoudhary.open2api.restcontroller.ExceptionHandler;

public class MissingHeaderException extends RuntimeException {
    public MissingHeaderException(String message) {
        super(message);
    }
}
