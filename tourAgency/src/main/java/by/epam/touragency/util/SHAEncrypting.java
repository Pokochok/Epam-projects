package by.epam.touragency.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHAEncrypting implements PasswordEncoder {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final SHAEncrypting INSTANCE = new SHAEncrypting();

    private SHAEncrypting(){}

    @Override
    public String encode(CharSequence password) {
        String encryptedPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.toString().getBytes(StandardCharsets.UTF_8));
            encryptedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Algorithm sha-256 not found", e);
            throw new RuntimeException(e);
        }
        return encryptedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
    }

    public static SHAEncrypting getInstance(){
        return INSTANCE;
    }
}
