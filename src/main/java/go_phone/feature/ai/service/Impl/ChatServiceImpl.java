package go_phone.feature.ai.service.Impl;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import go_phone.common.util.AiHelper;
import go_phone.common.util.SystemPrompt;
import go_phone.feature.ai.dto.request.ChatRequest;
import go_phone.feature.ai.dto.request.HelpRequest;
import go_phone.feature.ai.dto.response.ChatResponse;
import go_phone.feature.ai.dto.response.HelpResponse;
import go_phone.feature.ai.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @Override
    public String chat(ChatRequest request) {

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(SystemPrompt.SYSTEM, userMessage);

        return chatClient.prompt(prompt).call().content();
    }

    @Override
    public List<ChatResponse> chatWithImage(MultipartFile file, String message) {

        Prompt prompt = new Prompt(SystemPrompt.SYSTEM_HELP);

        Media media =
                Media.builder()
                        .mimeType(MimeTypeUtils.parseMimeType(file.getContentType()))
                        .data(file.getResource())
                        .build();

        ChatOptions chatOptions = ChatOptions.builder().temperature(0D).build();

        return chatClient
                .prompt(prompt)
                .user(promptUserSpec -> promptUserSpec.media(media).text(message))
                .call()
                .entity(new ParameterizedTypeReference<List<ChatResponse>>() {});
    }

    @Override
    public HelpResponse help(HelpRequest request) {

        String normalizedMessage = AiHelper.normalizeMessage(request.message());

        UserMessage userMessage = new UserMessage(request.message());

        Prompt prompt = new Prompt(SystemPrompt.SYSTEM, userMessage);

        String reply = chatClient.prompt(prompt).call().content();

        return new HelpResponse(
                request.username(),
                request.email(),
                request.type(),
                request.subject(),
                normalizedMessage,
                reply);
    }
}
