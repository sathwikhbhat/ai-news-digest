package com.sathwikhbhat.ai_news_digest.client;


import com.sathwikhbhat.ai_news_digest.dto.GeminiApiBody;
import com.sathwikhbhat.ai_news_digest.dto.GeminiApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GeminiClient {

    @Autowired
    @Qualifier("geminiRestClient")
    private RestClient restClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public GeminiApiResponse getTopHeadlines(GeminiApiBody apiBody) {
        return restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("v1beta/models/gemini-2.5-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .header("x-goog-api-key", apiKey)
                .body(apiBody)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(GeminiApiResponse.class);
    }

}