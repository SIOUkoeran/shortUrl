package com.example.shorturl.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    URL_NOT_FOUND(4040, "not found url"),
    BAD_REQUEST_URL(4000, "bad request url"),
    IllegalTimestampState(4000, "illegal timestamp state"),
    SEND_REDIRECT(4000, "send redirect exception"),
    IllegalTickState(4000, "illegal tick state");

    private final int errorCode;
    private final String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
