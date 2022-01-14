package com.example.shorturl.service;

import com.example.shorturl.repository.UrlRepository;
import com.example.shorturl.service.hashUtils.Hash;
import com.example.shorturl.url.Url;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
class UrlServiceTest {

    @Autowired
    UrlService urlService;

    @Autowired
    Hash hash;
    @Autowired
    UrlRepository urlRepository;
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
    @Transactional
    void saveSameOriginalUrl() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, DataIntegrityViolationException {
        String originalUrl = "www.naver.com";
        Url savedUrl = urlService.saveShortUrl(originalUrl);
        Url savedUrl2 = urlService.saveShortUrl(originalUrl);
        log.info("savedUrl shortUrl : {} , id {}", savedUrl.getShortUrl(), savedUrl.getId());
        log.info("savedUrl2 shortUrl : {}, id {}", savedUrl2.getShortUrl(), savedUrl2.getId());
        Assertions.assertThat(savedUrl.getShortUrl()).isEqualTo(savedUrl2.getShortUrl());
    }

    @Test
    @Transactional
    void saveDifferentOriginalUrl() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String originalUrl = "www.naver.com";
        String originalUrl1 = "www.naver1.com";
        Url savedUrl = urlService.saveShortUrl(originalUrl);
        Url savedUrl1 = urlService.saveShortUrl(originalUrl1);
        log.info("savedUrl shortUrl : {} , id {}", savedUrl.getShortUrl(), savedUrl.getId());
        log.info("savedUrl2 shortUrl : {}, id {}", savedUrl1.getShortUrl(), savedUrl1.getId());
        Assertions.assertThat(savedUrl.getShortUrl()).isNotEqualTo(savedUrl1.getShortUrl());
    }
    /**
     * url 저장 후 불러와서 리다렉션 되는지.
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    @Test
    @Transactional
    void findShortUrl() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String originalUrl = "www.naver.com";
        Url savedUrl = urlService.saveShortUrl(originalUrl);
        log.info("savedUrl = {}", savedUrl.getShortUrl());
        String findoriginalUrl = urlService.findUrl2(savedUrl.getShortUrl());


    }

}