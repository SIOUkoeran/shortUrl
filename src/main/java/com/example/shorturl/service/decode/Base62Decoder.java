package com.example.shorturl.service.decode;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Slf4j
@RequiredArgsConstructor
public class Base62Decoder implements Decoder{

    private final String base62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int radix = 62;

    @Override
    public BigInteger base62(String param) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger power = BigInteger.ONE;
        for (int i = 0; i < param.length(); i++) {
            sum = sum.add(BigInteger.valueOf(base62.indexOf(param.charAt(i))).multiply(power));
            power = power.multiply(BigInteger.valueOf(radix));
        }
        return sum;
    }

    @Override
    public long convertToLong(String param){
        long sum = 0;
        long pow = 1;
        for (int i = 0; i < param.length(); i++) {
            sum +=  base62.indexOf(param.charAt(i)) * pow;
            pow *= radix;
        }
        return sum;
    }
}
