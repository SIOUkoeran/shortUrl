package com.example.shorturl.service.decode;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public interface Decoder {
    public BigInteger base62(String value);
    public long convertToLong(String param);
}
