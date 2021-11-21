package com.example.demo.exceptions;

public class InvalidOperationTypeException extends RuntimeException {
    public InvalidOperationTypeException(String message) {
        super(message);
    }
}
