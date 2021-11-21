package com.example.demo.exceptions;

public class OperationNotFoundException extends RuntimeException {
    public OperationNotFoundException(String operationTypeId) {
        super(String.format("Operation Type with id %s not found.", operationTypeId));
    }
}
