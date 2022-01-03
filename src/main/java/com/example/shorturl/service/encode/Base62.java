package com.example.shorturl.service.encode;

import com.example.shorturl.service.hashUtils.Hash;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@RequiredArgsConstructor
@Component
@Slf4j
public class Base62 implements Encoder{

    private final String base62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String base62(BigInteger value) {
        StringBuffer stringBuffer = new StringBuffer();
        while (value.compareTo(BigInteger.valueOf(0)) > 0){
            stringBuffer.append(base62.charAt(value.mod(BigInteger.valueOf(62)).intValue()));
            value = value.divide(BigInteger.valueOf(62));
        }
        log.info("to base62 {}", stringBuffer.toString());
        return stringBuffer.toString();
    }
}
