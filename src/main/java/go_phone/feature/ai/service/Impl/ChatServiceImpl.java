package go_phone.feature.ai.service.Impl;

import go_phone.feature.ai.dto.ChatRequest;
import go_phone.feature.ai.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @Override
    public String chat(ChatRequest request) {
        return chatClient
                .prompt(request.message())
                .call()
                .content();
    }

}
