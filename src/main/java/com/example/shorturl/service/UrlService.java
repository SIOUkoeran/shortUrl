package com.example.shorturl.service;

import com.example.shorturl.exception.UrlException;
import com.example.shorturl.service.decode.Decoder;
import com.example.shorturl.service.encode.Encoder;
import com.example.shorturl.service.hashUtils.Hash;
import com.example.shorturl.url.Url;
import com.example.shorturl.repository.UrlRepository;

import com.example.shorturl.service.encode.UrlEncoder;
import com.example.shorturl.form.RequestUrlForm;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository repository;
    private final UrlEncoder encoder;
    private final Hash hash;
    private final Encoder encode;
    private final Decoder decoder;

    @Transactional
    public Url saveUrl(RequestUrlForm form){
        StringBuilder encoded = new StringBuilder();
        encoded.append(encoder.encode(form.getUrl()));
        while(this.repository.existsByShortUrl(String.valueOf(encoded)))
        {
               encoded.setLength(0);
               encoded.append(encoder.encode(form.getUrl()));
        }
        if (this.repository.existsByUrl(form.getUrl())) {
            return this.repository.findByUrl(form.getUrl()).get();
        }
        log.info("save url = {}", encoded);
        return this.repository.save(new Url(form.getUrl(),String.valueOf(encoded)));
    }

    @Transactional(readOnly = true)
    public Optional<Url> findUrl(String url){
        log.info("find url = {}", url);
        return this.repository.findByShortUrl(url);
    }

    public Url findUrlThrow(String url){
        log.info("find url = {}", url);
        return this.repository.findByShortUrl(url)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 url 요청"));
    }

    @Transactional(readOnly = true)
    public String findUrl2(String shortUrl) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        log.info("find url = {}", shortUrl);
        Url url = this.repository.findByShortUrl(shortUrl)
                .orElseThrow(UrlException::new);
        BigInteger encryptedUrl = decoder.base62(url.getUrl());
        String encrypted =  encryptedUrl.toString();
        log.info("복호화 10진수 : {}", encrypted);
        String originalUrl = hash.decrypt(encrypted);
        log.info("originalUrl {}", originalUrl);
        return originalUrl;
    }

    @Transactional
    public Url saveShortUrl(String originalUrl) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encryptedBase62 = toBase62(createEncryptedUrl(originalUrl));
        Optional<Url>  findUrl = this.repository.findByUrl(encryptedBase62);
        if (findUrl.isPresent())
            return findUrl.get();
        StringBuffer stringBuffer = new StringBuffer();
        int idx = 0;
        Url url;
        while (true){
            stringBuffer.append(encryptedBase62, idx, idx + 7);
            log.info("subString = {}", stringBuffer);
            try{
                return this.repository.save(new Url(encryptedBase62, String.valueOf(stringBuffer)));
            }catch (DataIntegrityViolationException e){
                log.error("duplicated ShortUrl : {}", stringBuffer);
                idx++;
                stringBuffer.setLength(0);
            }
        }
    }

    private String createEncryptedUrl(String originalUrl) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return hash.encrypt(originalUrl);
    }
    private String toBase62(String input){
        BigInteger bigInteger = new BigInteger(input, 16) ;
        log.info("10진수 : {}", bigInteger);
        return encode.base62(bigInteger);
    }
    private String decrpytUrl(String encrypted) throws InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        return hash.decrypt(encrypted);
    }

}
