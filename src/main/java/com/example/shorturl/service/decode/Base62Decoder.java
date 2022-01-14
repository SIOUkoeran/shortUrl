package com.example.shorturl.service.decode;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
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
//    public BigInteger base62(String value) {
//        BigInteger result = BigInteger.ZERO;
//        int power = 1;
//        int idx;
//        if (value == null)
//            throw new IllegalArgumentException("String is null");
//        for (int i = 0; i < value.length(); i++) {
//            idx = base62.indexOf(value.charAt(i));
//            result = result.add(BigInteger.valueOf(idx).multiply(BigInteger.valueOf(62).pow(power)));
//            log.info("result ::: {}", result);
//            power *= radix;
//        }
//        log.info("Big Integer value{}" , result);
//        return result;
//    }
}
