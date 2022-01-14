package com.example.shorturl.service.encode;

import com.example.shorturl.service.hashUtils.AES;
import com.example.shorturl.service.hashUtils.Hash;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class Base62EncoderDecoderTest {

    @Test
    void toBase62() throws InvalidAlgorithmParameterException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Encoder base62 = new Base62Encoder();
        Hash aes = new AES();
        String url = "www.naver.com";
        String encrypted = aes.encrypt(url);
        String base62String = base62.base62(new BigInteger(encrypted, 16));
        String base62StringCopy = base62.base62(new BigInteger(encrypted, 16));
        assertThat(base62String).isEqualTo(base62StringCopy);
    }
}