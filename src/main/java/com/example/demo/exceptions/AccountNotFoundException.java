package com.example.demo.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String id) {
        super(String.format("Account with id %s does not exist.", id));
    }
}
