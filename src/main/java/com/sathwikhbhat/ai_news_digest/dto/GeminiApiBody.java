package com.sathwikhbhat.ai_news_digest.dto;

import java.util.List;

public record GeminiApiBody(List<Content> contents) {

    public record Content(List<Part> parts) {

        public record Part(String text) {
        }

    }

}