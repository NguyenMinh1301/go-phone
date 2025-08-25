package go_phone.feature.ai.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import go_phone.feature.ai.dto.request.ChatRequest;
import go_phone.feature.ai.dto.request.HelpRequest;
import go_phone.feature.ai.dto.response.ChatResponse;
import go_phone.feature.ai.dto.response.HelpResponse;

public interface ChatService {

    String chat(ChatRequest request);

    List<ChatResponse> chatWithImage(MultipartFile file, String message);

    HelpResponse help(HelpRequest helpRequest);
}
