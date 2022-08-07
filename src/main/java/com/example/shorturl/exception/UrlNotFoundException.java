package com.example.shorturl.exception;

public class UrlNotFoundException extends CustomException{

    public UrlNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
