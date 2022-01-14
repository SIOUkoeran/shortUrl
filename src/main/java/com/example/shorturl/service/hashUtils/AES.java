package com.example.shorturl.service.hashUtils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Component
@Slf4j
public class AES implements Hash{

    private static final String algorithm = "AES/CBC/PKCS5Padding";
    private static final String key = "1293234123987529";
    private static final String iv = key.substring(0,16);

    @Override
    public String encrypt(String originalUrl) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec secretKeySpec = createKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = createIvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(originalUrl.getBytes("UTF-8"));
        log.info("encrypted {}", encrypted.toString());
        return toHex(encrypted);
    }
    
    private String toHex(byte[] inputBytes){
        if (inputBytes == null || inputBytes.length == 0) {
            throw new IllegalArgumentException();
        }
        StringBuffer sb = new StringBuffer(inputBytes.length * 2);
        String hexNumber;
        for (int x = 0; x < inputBytes.length; x++) {
            hexNumber = "0" + Integer.toHexString(0xff & inputBytes[x]);
            sb.append(hexNumber.substring(hexNumber.length() - 2));
        }
        log.info("encrypted url {}", sb.substring(1));
        return sb.substring(1);
    }

    @Override
    public String decrypt(String hash) throws UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException,
            InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance(algorithm);
        SecretKeySpec secretKeySpec = createKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = createIvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decrypted = cipher.doFinal(hash.getBytes());
        return new String(decrypted, "UTF-8");
    }

    private SecretKeySpec createKeySpec(String key, String algorithm){
        return new SecretKeySpec(key.getBytes(), algorithm);
    }


    private IvParameterSpec createIvParameterSpec(String iv){
        return new IvParameterSpec(iv.getBytes());
    }
}
