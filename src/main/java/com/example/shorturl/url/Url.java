package com.example.shorturl.url;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Url {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @Column(unique = true)
    private String shortUrl;

    public Url(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
    }

    protected Url() {
    }
}
