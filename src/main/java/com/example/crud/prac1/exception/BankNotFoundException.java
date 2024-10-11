package com.example.crud.prac1.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(String message) {
        super(message);
    }
}
