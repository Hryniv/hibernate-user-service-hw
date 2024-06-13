package mate.academy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtil {
    private static final String CRYPTO_ALGORITHM = "SHA-512";

    private HashUtil() {
    }

    public static byte[] getSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(CRYPTO_ALGORITHM);
            messageDigest.update(salt);
            for (byte b: messageDigest.digest(password.getBytes())) {
                hashedPassword.append(String.format("%02x", b));
            }

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    String.format("Couldn't create hash using %s algorithm", CRYPTO_ALGORITHM), e);
        }
        return hashedPassword.toString();
    }
}
