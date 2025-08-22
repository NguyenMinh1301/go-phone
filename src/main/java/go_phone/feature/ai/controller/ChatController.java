package go_phone.feature.ai.controller;

import go_phone.common.constants.ApiConstants;
import go_phone.feature.ai.dto.ChatRequest;
import go_phone.feature.ai.service.ChatService;
import go_phone.feature.ai.service.Impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Chat.BASE)
public class ChatController {
    private final ChatServiceImpl chatService;

    @PostMapping("")
    String chat(@RequestBody ChatRequest chatRequest) {
        return chatService.chat(chatRequest);
    }

}
