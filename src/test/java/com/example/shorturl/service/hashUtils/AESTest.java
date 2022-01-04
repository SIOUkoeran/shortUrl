package com.example.shorturl.service.hashUtils;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AESTest {

    @Test
    void encrypt() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Hash encryptUtil = new AES();
        String url = "www.naver.com";
        String encrypt = encryptUtil.encrypt(url);
        log.info("encrypt result = {}", encrypt);
        log.info("transform long = {}", new BigInteger(String.valueOf(encrypt), 16));
        String encryptCopy = encryptUtil.encrypt(url);
        Assertions.assertThat(encrypt).isEqualTo(encryptCopy);
    }

}