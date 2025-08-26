package go_phone.feature.ai.dto.request;

import jakarta.validation.constraints.*;

public record HelpRequest(
        // 1) Danh tính – liên hệ
        @NotBlank @Size(max = 50) String username,
        @Email @Size(max = 120) String email,

        // 2) Phân loại & kênh
        @NotNull SupportType type, // GENERAL/ORDER/PAYMENT/...
        @NotNull SupportChannel channel, // WEB/EMAIL/CHAT/PHONE/SOCIAL

        // 3) Nội dung
        @NotBlank @Size(max = 120) String subject,
        @NotBlank @Size(max = 4000) String message,

        // 4) Ngữ cảnh (tùy chọn)
        @Size(max = 40) String orderCode,
        Long orderId,
        Long productId,
        @Size(max = 64) String productSku,

        // 5) Ngôn ngữ & kỹ thuật
        @Size(max = 10) String locale, // vi-VN
        @Size(max = 40) String timezone, // Asia/Ho_Chi_Minh
        @Size(max = 45) String clientIp,
        @Size(max = 255) String userAgent,
        @Size(max = 40) String appVersion,
        @Size(max = 40) String os) {
    public enum SupportType {
        GENERAL,
        ORDER,
        PAYMENT,
        WARRANTY,
        RETURN,
        TECHNICAL,
        ACCOUNT,
        BILLING,
        SHIPPING,
        FEEDBACK,
        OTHER
    }

    public enum SupportChannel {
        WEB,
        EMAIL,
        CHAT,
        PHONE,
        SOCIAL
    }
}
