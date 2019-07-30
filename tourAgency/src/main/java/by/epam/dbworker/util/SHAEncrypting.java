package by.epam.dbworker.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SHAEncrypting {
    private static final Logger LOGGER = LogManager.getLogger();

    public static String hidePassword(String password) {
        String encryptedPassword = "default";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            encryptedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Algorithm sha-256 not found", e);
            //throw new ConnectionPoolException(e);
            // FIXME: 12/07/2019 стоит ли выкидывать Exception
        }
        return encryptedPassword;
    }
}
