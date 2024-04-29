package com.udacity.jwdnd.course1.cloudstorage.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

@Service
public class EncryptionService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_LENGTH = 256;
    private static final int ITERATION_COUNT = 65536;
    private static final int SALT_LENGTH = 16;

    public String encryptValue(String data, String password) {
        try {
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            byte[] encryptedValue = cipher.doFinal(data.getBytes("UTF-8"));
            byte[] encryptedValueWithSalt = new byte[salt.length + encryptedValue.length];
            System.arraycopy(salt, 0, encryptedValueWithSalt, 0, salt.length);
            System.arraycopy(encryptedValue, 0, encryptedValueWithSalt, salt.length, encryptedValue.length);
            return Base64.getEncoder().encodeToString(encryptedValueWithSalt);
        } catch (Exception e) {
            logger.error("Encryption error", e);
            throw new RuntimeException("Failed to encrypt data", e);
        }
    }

    public String decryptValue(String data, String password) {
        try {
            byte[] decodedData = Base64.getDecoder().decode(data);
            byte[] salt = Arrays.copyOf(decodedData, SALT_LENGTH);
            byte[] encryptedData = Arrays.copyOfRange(decodedData, SALT_LENGTH, decodedData.length);

            SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKey secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
            byte[] decrypted = cipher.doFinal(encryptedData);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("Decryption error", e);
            throw new RuntimeException("Failed to decrypt data", e);
        }
    }
}
