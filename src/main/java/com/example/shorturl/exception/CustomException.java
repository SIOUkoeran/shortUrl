package com.example.shorturl.exception;

public class CustomException extends RuntimeException {
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
    }
}
