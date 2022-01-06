package com.example.shorturl.service;

import com.example.shorturl.service.encode.Base62;
import com.example.shorturl.service.encode.Encoder;
import com.example.shorturl.service.hashUtils.AES;
import com.example.shorturl.service.hashUtils.Hash;
import com.example.shorturl.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
class UrlServiceTest {

    @Autowired
    UrlService urlService;

    /**
     *
     * input -> String originalUrl;
     * 암호화 된 전체 url은 같지만, 8자리로 짧게 만든 URL은 서로가 다르다.
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    @Test
    void saveShortUrl() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, DataIntegrityViolationException {
        String originalUrl = "www.naver.com";
        Url savedUrl = urlService.saveShortUrl(originalUrl);
        log.info("savedUrl = {}", savedUrl.getShortUrl());
        Url savedUrl2 = urlService.saveShortUrl(originalUrl);
        log.info("savedUrl2 = {}", savedUrl2.getShortUrl());
        assertThat(savedUrl.getUrl()).isEqualTo(savedUrl2.getUrl());
        assertThat(savedUrl.getShortUrl()).isNotEqualTo(savedUrl2.getShortUrl());
    }
}