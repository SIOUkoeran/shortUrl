package com.example.shorturl.url;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Url {

    @Id @GeneratedValue
    private Long id;

    private String url;

    private String shortUrl;

    public Url(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
    }

    protected Url() {
    }
}
