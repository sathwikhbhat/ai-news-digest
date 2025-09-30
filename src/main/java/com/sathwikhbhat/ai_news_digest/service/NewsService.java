package com.sathwikhbhat.ai_news_digest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.sathwikhbhat.ai_news_digest.client.NewsClient;
import com.sathwikhbhat.ai_news_digest.dto.GeminiApiResponse;
import com.sathwikhbhat.ai_news_digest.dto.NewsApiResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NewsService {

    @Autowired
    private NewsClient newsClient;

    @Autowired
    private GeminiService geminiService;

    public GeminiApiResponse getTopHeadlines(boolean render) {
        log.info("Fetching top headlines from News API");
        NewsApiResponse newsApiResponse = newsClient.fetchTopHeadlines();
        log.info("Fetched {} articles", newsApiResponse.articles().size());

        log.info("Sending articles to Gemini API for summarization");
        GeminiApiResponse geminiApiResponse = geminiService.getGeminiApiResponse(newsApiResponse, render);
        log.info("Received summary from Gemini API");
        return geminiApiResponse;
    }

    @CacheEvict(value = { "topHeadlines", "geminiResponse" }, allEntries = true)
    public void clearCache() {
        log.info("Clearing all cache entries");
    }

}