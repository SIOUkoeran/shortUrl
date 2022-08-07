package com.example.shorturl.exception;

public class IllegalTimeStampStateException extends CustomException{

    public IllegalTimeStampStateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
