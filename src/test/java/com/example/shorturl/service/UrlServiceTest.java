package com.example.shorturl.service;

import com.example.shorturl.repository.UrlRepository;
import com.example.shorturl.url.Url;
import lombok.extern.slf4j.Slf4j;
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
    void saveShortUrl() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, DataIntegrityViolationException {
        String originalUrl = "www.naver.com";
        Url savedUrl = urlService.saveShortUrl(originalUrl);
        Url savedUrl2 = urlService.saveShortUrl(originalUrl);

        Optional<Url> findUrl = this.urlRepository.findById(1L);
        log.info("findUrl {} {}", findUrl.get().getUrl(), findUrl.get().getId());
        log.info("savedUrl2 Id {}", savedUrl2.getId());
        assertThat(savedUrl.getUrl()).isEqualTo(savedUrl2.getUrl());
        assertThat(savedUrl.getShortUrl()).isNotEqualTo(savedUrl2.getShortUrl());

    }
}