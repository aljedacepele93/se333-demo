package edu.depaul.se331.chatbot.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SafetyFilterService {

    private final List<String> blockedWords = List.of(
            "hack",
            "malware",
            "attack",
            "illegal",
            "steal"
    );

    public boolean isSafe(String message) {

        String lowerMessage = message.toLowerCase();

        for (String word : blockedWords) {
            if (lowerMessage.contains(word)) {
                return false;
            }
        }

        return true;
    }
}
