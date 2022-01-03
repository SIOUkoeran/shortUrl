package com.example.shorturl.service;

import com.example.shorturl.service.encode.Base62;
import com.example.shorturl.service.encode.Encoder;
import com.example.shorturl.service.hashUtils.AES;
import com.example.shorturl.service.hashUtils.Hash;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Slf4j
@SpringBootTest
class UrlServiceTest {

    @Autowired
    UrlService urlService;

    /**
     * urlService.createUrl is private.
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    @Test
    void createBase62Url() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
//        String url = "www.naver.com";
//        String urlToBase62 = urlService.createUrl(url);
//        log.info(urlToBase62);

    }
}