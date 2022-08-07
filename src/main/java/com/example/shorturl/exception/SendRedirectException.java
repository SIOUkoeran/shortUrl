package com.example.shorturl.exception;

public class SendRedirectException  extends CustomException{
    public SendRedirectException(ErrorCode errorCode) {
        super(errorCode);
    }
}
