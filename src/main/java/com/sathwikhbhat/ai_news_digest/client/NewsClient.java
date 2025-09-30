package com.sathwikhbhat.ai_news_digest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.sathwikhbhat.ai_news_digest.dto.NewsApiResponse;

@Service
public class NewsClient {

    @Autowired
    @Qualifier("newsRestClient")
    private RestClient restClient;

    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.country}")
    private String country;

    @Cacheable(value = "topHeadlines")
    public NewsApiResponse fetchTopHeadlines() {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("v2/top-headlines")
                        .queryParam("country", country)
                        .queryParam("apiKey", apiKey)
                        .build())
                .retrieve()
                .body(NewsApiResponse.class);
    }

}