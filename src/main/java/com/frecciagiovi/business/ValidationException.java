package com.frecciagiovi.business;


public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException() {
        super();
    }
}
