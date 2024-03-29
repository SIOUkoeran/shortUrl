package com.example.shorturl.exhandler;

import lombok.Data;

@Data
public class ErrorResult {
    private int code;
    private String message;

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
