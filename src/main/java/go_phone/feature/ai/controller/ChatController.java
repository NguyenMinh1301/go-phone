package go_phone.feature.ai.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import go_phone.common.constants.ApiConstants;
import go_phone.common.response.ApiResponse;
import go_phone.common.response.ResponseHandler;
import go_phone.feature.ai.dto.request.ChatRequest;
import go_phone.feature.ai.dto.request.HelpRequest;
import go_phone.feature.ai.dto.response.HelpResponse;
import go_phone.feature.ai.service.Impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConstants.Ai.BASE)
public class ChatController {
    private final ChatServiceImpl chatService;

    @PostMapping(ApiConstants.Ai.CHAT)
    public ResponseEntity<ApiResponse<String>> chat(@RequestBody ChatRequest chatRequest) {
        return ResponseHandler.success(chatService.chat(chatRequest));
    }

    @PostMapping(ApiConstants.Ai.CHAT_WITH_IMAGE)
    public ResponseEntity<ApiResponse<List>> chatWithImage(
            @RequestParam("file") MultipartFile file, @RequestParam("message") String message) {
        return ResponseHandler.success(chatService.chatWithImage(file, message));
    }

    @PostMapping(ApiConstants.Ai.HELP)
    public ResponseEntity<ApiResponse<HelpResponse>> help(@RequestBody HelpRequest helpRequest) {
        return ResponseHandler.success(chatService.help(helpRequest));
    }
}
