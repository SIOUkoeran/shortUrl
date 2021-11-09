package com.example.shorturl.service;

import com.example.shorturl.url.Url;
import com.example.shorturl.repository.UrlRepository;

import com.example.shorturl.encode.UrlEncoder;
import com.example.shorturl.form.RequestUrlForm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
public class UrlService {

    private final UrlRepository repository;
    private final UrlEncoder encoder;

    @Autowired
    public UrlService(UrlRepository repository, UrlEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Url saveUrl(RequestUrlForm form){

        StringBuilder encoded = new StringBuilder();
        encoded.append(encoder.encode(form.getUrl()));

        while(this.repository.existsByShortUrl(String.valueOf(encoded)))
        {
               encoded.setLength(0);
               encoded.append(encoder.encode(form.getUrl()));
        }

        if (this.repository.existsByUrl(form.getUrl())) {
            return this.repository.findByUrl(form.getUrl()).get();
        }

        log.info("save url = {}", encoded);

        return this.repository.save(new Url(form.getUrl(),String.valueOf(encoded)));
    }

    public Optional<Url> findUrl(String url){

        log.info("find url = {}", url);
        return this.repository.findByShortUrl(url);
    }

}
