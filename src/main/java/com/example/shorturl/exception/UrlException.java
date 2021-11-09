package com.example.shorturl.exception;


public class UrlException extends RuntimeException {

    public UrlException() {
    }

    public UrlException(String message) {
        super(message);
    }

    public UrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlException(Throwable cause) {
        super(cause);
    }

    public UrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
