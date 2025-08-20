package go_phone.common.util;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class OrderNumberGenerator {
    private static final long MIN = 1_000_000_000L;   // 10 chữ số, bắt đầu từ 1_000_000_000
    private static final long MAX = 9_999_999_999L;   // PayOS giới hạn
    private static final long RANGE = (MAX - MIN) + 1; // = 9_000_000_000

    private final SecureRandom rnd = new SecureRandom();

    // Trả về mã đơn hàng 10 chữ số trong [1_000_000_000, 9_999_999_999]
    public long next10Digits() {
        long v = Math.abs(rnd.nextLong()) % RANGE;
        return MIN + v;
    }
}
