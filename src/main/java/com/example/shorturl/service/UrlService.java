package com.example.shorturl.service;

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
import org.springframework.transaction.annotation.Transactional;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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

    public Optional<Url> findUrl(String url){

        log.info("find url = {}", url);
        return this.repository.findByShortUrl(url);
    }

    public Url saveShortUrl(String originalUrl) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String encryptedBase62 = toBase62(createEncryptedUrl(originalUrl));
        StringBuffer stringBuffer = new StringBuffer();
        int idx = 0;
        while (true){
            stringBuffer.append(encryptedBase62, idx, idx + 7);
            log.info("subString = {}", stringBuffer);
            try{
                this.repository.save(new Url(encryptedBase62, String.valueOf(stringBuffer)));
                break;
            }catch (DataIntegrityViolationException e){
                log.error("duplicated ShortUrl : {}", stringBuffer);
                idx++;
                stringBuffer.setLength(0);
            }
        }
        return new Url(encryptedBase62, String.valueOf(stringBuffer));
    }



    private String createEncryptedUrl(String originalUrl) throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return hash.encrypt(originalUrl);
    }
    private String toBase62(String input){
        return encode.base62(new BigInteger(input, 16));
    }

}
