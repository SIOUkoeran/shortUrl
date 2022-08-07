package com.example.shorturl.service.encode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@RequiredArgsConstructor
@Component
@Slf4j
public class Base62Encoder implements Encoder{

    private final String base62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @Override
    public String base62(BigInteger value) {
        StringBuffer stringBuffer = new StringBuffer();
        while (value.compareTo(BigInteger.valueOf(0)) > 0){
            stringBuffer.append(base62.charAt(value.mod(BigInteger.valueOf(62)).intValue()));
            value = value.divide(BigInteger.valueOf(62));
        }
        log.info("result base62 {}", stringBuffer);
        return String.valueOf(stringBuffer);
    }

    @Override
    public String base62(long value) {
        log.info("request converting base62 {}", value);
        StringBuffer stringBuffer = new StringBuffer();
        while (value > 0){
            stringBuffer.append(base62.charAt((int) (value % 62)));
            value = value / 62;
        }
        log.info("result base62 {}", stringBuffer);
        return String.valueOf(stringBuffer);
    }
}
