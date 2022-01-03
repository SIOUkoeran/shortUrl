package com.example.shorturl.service.encode;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public interface Encoder {
    public String base62(BigInteger value);
}
