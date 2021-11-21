package com.example.demo.exceptions;

public class AccountAlreadyExistsForDocumentException extends RuntimeException {
    public AccountAlreadyExistsForDocumentException(String documentNumber) {
        super(String.format("Account already exists for document %s", documentNumber));
    }
}
