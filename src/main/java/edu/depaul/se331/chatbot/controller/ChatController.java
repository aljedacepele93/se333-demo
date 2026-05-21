package edu.depaul.se331.chatbot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.se331.chatbot.model.ChatMessage;
import edu.depaul.se331.chatbot.model.ChatRequest;
import edu.depaul.se331.chatbot.model.ChatResponse;
import edu.depaul.se331.chatbot.service.ChatService;
import edu.depaul.se331.chatbot.service.SafetyFilterService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;
    private final SafetyFilterService safetyFilterService;

    public ChatController(ChatService chatService,
                          SafetyFilterService safetyFilterService) {
        this.chatService = chatService;
        this.safetyFilterService = safetyFilterService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        if (request.getMessage() == null || request.getMessage().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new ChatResponse("Please provide a non-empty message."));
        }

        if (!safetyFilterService.isSafe(request.getMessage())) {
            return ResponseEntity.badRequest()
                    .body(new ChatResponse("Message blocked by safety filter."));
        }

        String reply = chatService.chat(request.getMessage().trim());
        return ResponseEntity.ok(new ChatResponse(reply));
    }

    @DeleteMapping("/history")
    public ResponseEntity<Map<String, String>> clearHistory() {
        chatService.resetHistory();
        return ResponseEntity.ok(Map.of("status", "Conversation history cleared."));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ChatMessage>> getHistory() {
        return ResponseEntity.ok(chatService.getHistory());
    }
}