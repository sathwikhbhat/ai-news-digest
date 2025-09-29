package com.sathwikhbhat.ai_news_digest.dto;

import java.util.List;

public record GeminiApiResponse(List<Candidate> candidates) {

    public record Candidate(Content content) {

        public record Content(List<Part> parts) {

            public record Part(String text) {
            }

        }

    }

}