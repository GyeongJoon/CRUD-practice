package com.example.crud.prac1.exception;

public class BankAlreadyExistsException extends RuntimeException {
    public BankAlreadyExistsException(String message) {
        super(message);
    }
}
