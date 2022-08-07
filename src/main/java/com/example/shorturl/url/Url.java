package com.example.shorturl.url;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.net.URL;

@Entity
@Getter @Setter
public class Url {

    @Id
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

    public Url(Long id, String url, String shortUrl){
        this.id = id;
        this.url = url;
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}
