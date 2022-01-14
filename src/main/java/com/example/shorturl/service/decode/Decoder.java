package com.example.shorturl.service.decode;


import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public interface Decoder {
    public BigInteger base62(String value);
}
