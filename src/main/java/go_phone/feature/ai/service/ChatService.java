package go_phone.feature.ai.service;

import go_phone.feature.ai.dto.ChatRequest;
import go_phone.feature.ai.service.Impl.ChatServiceImpl;
import org.springframework.ai.chat.client.ChatClient;

public interface ChatService {

    String chat(ChatRequest request);

}
