package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface PasswordHashingUtils {
    static String MD5Hashing(String password) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(password.getBytes());
        byte[] bytes = m.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b:bytes) {
            stringBuffer.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
}
