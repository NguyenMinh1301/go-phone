package go_phone.common.util;

import java.util.Objects;

import jakarta.annotation.Nullable;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import go_phone.feature.ai.dto.request.HelpRequest;

@Component
public class AiHelper {

    public static String normalizeMessage(String s) {
        if (s == null) return "";
        // trim + collapse whitespace, giới hạn 4000
        String out = s.strip().replaceAll("\\s+", " ");
        return out.length() > 4000 ? out.substring(0, 4000) : out;
    }

    public String safe(String s) {
        return s == null ? "" : s;
    }

    public String buildHelpPrompt(HelpRequest r, String normalizedMsg) {
        StringBuilder sb = new StringBuilder(512);
        sb.append("Yêu cầu hỗ trợ từ khách hàng:\n")
                .append("- Họ tên/username: ")
                .append(safe(r.username()))
                .append('\n')
                .append("- Email: ")
                .append(safe(r.email()))
                .append('\n')
                .append("- Loại: ")
                .append(Objects.toString(r.type(), "GENERAL"))
                .append('\n')
                .append("- Kênh: ")
                .append(Objects.toString(r.channel(), "WEB"))
                .append('\n')
                .append("- Chủ đề: ")
                .append(safe(r.subject()))
                .append('\n')
                .append("- Nội dung: ")
                .append(normalizedMsg)
                .append('\n');

        // Ngữ cảnh thêm (nếu có)
        if (r.orderCode() != null) sb.append("- Mã đơn: ").append(r.orderCode()).append('\n');
        if (r.orderId() != null) sb.append("- OrderId: ").append(r.orderId()).append('\n');
        if (r.productId() != null) sb.append("- ProductId: ").append(r.productId()).append('\n');
        if (r.productSku() != null) sb.append("- SKU: ").append(r.productSku()).append('\n');

        // Thông tin kỹ thuật (nếu có)
        if (r.locale() != null) sb.append("- Locale: ").append(r.locale()).append('\n');
        if (r.timezone() != null) sb.append("- Timezone: ").append(r.timezone()).append('\n');
        if (r.clientIp() != null) sb.append("- IP: ").append(r.clientIp()).append('\n');
        if (r.userAgent() != null) sb.append("- UA: ").append(r.userAgent()).append('\n');
        if (r.appVersion() != null) sb.append("- AppVersion: ").append(r.appVersion()).append('\n');
        if (r.os() != null) sb.append("- OS: ").append(r.os()).append('\n');

        sb.append("\nHãy chẩn đoán và hướng dẫn từng bước, ngắn gọn, rõ ràng.");
        return sb.toString();
    }

    public static boolean hasFile(@Nullable MultipartFile file) {
        return file != null && !file.isEmpty() && file.getSize() > 0;
    }
}
