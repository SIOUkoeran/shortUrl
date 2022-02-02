package com.example.shorturl.service.hashUtils;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
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
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.apache.tomcat.util.codec.binary.Base64.*;


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
        byte[] result = Base64.encodeBase64(encrypted);
        return toHex(result);
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
        return sb.toString();
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
        byte[] encodedBytes = toBytes(hash);
        byte[] decrypted = cipher.doFinal(Base64.decodeBase64(encodedBytes));
        return new String(decrypted, "UTF-8");
    }

    private byte[] toBytes(String encoded){
        if (encoded == null || encoded.length() == 0) { return null; }
        byte[] ba = new byte[encoded.length() / 2];
        for (int i = 0; i < ba.length; i++) {
            ba[i] = (byte) (Integer.parseInt(encoded.substring(2 * i, 2 * i + 2), 16));
        }
        return ba;

    }
    private SecretKeySpec createKeySpec(String key, String algorithm) throws UnsupportedEncodingException {
        return new SecretKeySpec(key.getBytes("UTF-8"), algorithm);
    }

    private IvParameterSpec createIvParameterSpec(String iv) throws UnsupportedEncodingException {
        return new IvParameterSpec(iv.getBytes("UTF-8"));
    }

    private   byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
}
