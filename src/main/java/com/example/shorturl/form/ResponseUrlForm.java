package com.example.shorturl.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseUrlForm implements Serializable {

    private String shortUrl;
    private String url;
    public ResponseUrlForm(String shortUrl, String url) {
        this.shortUrl = shortUrl;
        this.url = url;
    }
}
