package com.udacity.jwdnd.course1.cloudstorage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class HashService {

    public final Logger logger = LoggerFactory.getLogger(HashService.class);
    private final SecureRandom secureRandom = new SecureRandom();

    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;
        int iterCount = 12288;
        int derivedKeyLength = 256;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), iterCount, derivedKeyLength * 8);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            hashedValue = factory.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error("Error creating hash: " +e.getMessage());
            throw new RuntimeException("Error creating hash",e);
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }
    public String generateSalt(){
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

}
