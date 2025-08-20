package go_phone.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashingUtils {

    private HashingUtils() {}

    public static String sha256(String raw) {
        try {
            var md = MessageDigest.getInstance("SHA-256");
            var out = md.digest(raw.getBytes(StandardCharsets.UTF_8));
            var sb = new StringBuilder();
            for (byte b : out) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

}
