package com.example.demo.exceptions;

public class RequestNotValidException extends RuntimeException {
    public RequestNotValidException(String message) {
        super(message);
    }
}
