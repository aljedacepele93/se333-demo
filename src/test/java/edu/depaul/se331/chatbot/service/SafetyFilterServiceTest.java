package edu.depaul.se331.chatbot.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SafetyFilterServiceTest {

    private final SafetyFilterService safetyFilterService = new SafetyFilterService();

    @Test
    void safeMessageReturnsTrue() {
        assertTrue(safetyFilterService.isSafe("Hello, can you explain Java interfaces?"));
    }

    @Test
    void blockedWordHackReturnsFalse() {
        assertFalse(safetyFilterService.isSafe("How do I hack a system?"));
    }

    @Test
    void blockedWordMalwareReturnsFalse() {
        assertFalse(safetyFilterService.isSafe("Can you help me create malware?"));
    }

    @Test
    void blockedWordIsCaseInsensitive() {
        assertFalse(safetyFilterService.isSafe("How do I HACK something?"));
    }
}