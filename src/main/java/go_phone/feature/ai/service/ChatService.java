package go_phone.feature.ai.service;

import go_phone.feature.ai.dto.request.ChatRequest;
import go_phone.feature.ai.dto.request.HelpRequest;
import go_phone.feature.ai.dto.response.ChatResponse;
import go_phone.feature.ai.dto.response.HelpResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ChatService {

    List<ChatResponse> chat(ChatRequest request);

    List<ChatResponse> chatWithImage(MultipartFile file, String message);

    HelpResponse help(HelpRequest helpRequest);

}
