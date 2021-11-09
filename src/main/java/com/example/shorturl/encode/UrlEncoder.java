package com.example.shorturl.encode;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
public class UrlEncoder {

    public String encode(String url){

        StringBuffer encoding = new StringBuffer();
        encoding.append(String.valueOf(UUID.randomUUID()),0,8);

        log.info("encoding url = {}", String.valueOf(encoding));

        if (encoding.length() > 8){
            return String.valueOf(encoding.substring(0,8));
        }
        return String.valueOf(encoding);

    }

}
