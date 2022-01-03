package com.example.shorturl.service.encode;


import com.example.shorturl.service.hashUtils.Hash;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class UrlEncoder {
    private final Hash hash;

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
