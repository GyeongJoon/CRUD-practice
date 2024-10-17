package com.example.crud.prac2.exception;

import lombok.Getter;

@Getter
public class appException extends RuntimeException {
    private final ErrorCode ErrorCode;

    public appException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.ErrorCode = errorCode;
    }
}
