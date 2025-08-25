package go_phone.feature.ai.dto.response;

import go_phone.feature.ai.dto.request.HelpRequest.SupportType;

public record HelpResponse(
        String username,
        String email,
        SupportType type,
        String subject,
        String message, // nguyên văn message user đã gửi (đã normalize)
        String reply // câu trả lời từ AI
        ) {}
