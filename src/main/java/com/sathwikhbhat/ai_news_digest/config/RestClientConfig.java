package com.sathwikhbhat.ai_news_digest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient newsRestClient(RestClient.Builder builder, @Value("${news.api.url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }

    @Bean
    public RestClient geminiRestClient(RestClient.Builder builder, @Value("${gemini.api.url}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }

}