package com.example.shorturl.exception;

public class IllegalTickStateException extends CustomException{
    public IllegalTickStateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
