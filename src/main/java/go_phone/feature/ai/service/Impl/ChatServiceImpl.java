package go_phone.feature.ai.service.Impl;

import go_phone.feature.ai.dto.ChatRequest;
import go_phone.feature.ai.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @Override
    public String chat(ChatRequest request) {

        SystemMessage systemMessage = new SystemMessage("""
                Your name is gophone
                """);

        UserMessage userMessage = new UserMessage(request.message());

        Prompt promt = new Prompt(systemMessage, userMessage);

        return chatClient
                .prompt(promt)
                .call()
                .content();
    }

}
