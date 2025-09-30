package com.sathwikhbhat.ai_news_digest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sathwikhbhat.ai_news_digest.client.GeminiClient;
import com.sathwikhbhat.ai_news_digest.dto.GeminiApiBody;
import com.sathwikhbhat.ai_news_digest.dto.GeminiApiResponse;
import com.sathwikhbhat.ai_news_digest.dto.NewsApiResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeminiService {

    @Autowired
    private GeminiClient geminiClient;

    @Cacheable(value = "geminiResponse", key = "#newsApiResponse.hashCode() + '_' + #render")
    public GeminiApiResponse getGeminiApiResponse(NewsApiResponse newsApiResponse, boolean render) {
        log.info("Calling Gemini API for summarization (render={})", render);
        String prompt = getGeminiPrompt(newsApiResponse, render);
        GeminiApiBody apiBody = new GeminiApiBody(
                List.of(new GeminiApiBody.Content(
                        List.of(new GeminiApiBody.Content.Part(prompt)))));
        return geminiClient.getTopHeadlines(apiBody);
    }

    public String getGeminiPrompt(NewsApiResponse newsApiResponse, boolean render) {
        StringBuilder promptBuilder = new StringBuilder();
        if (render) {
            promptBuilder.append("""
                    You are an expert HTML generator.
                    Summarize the following news articles and return only the full HTML code for a complete webpage.
                    Make the page look clean and readable using inline CSS or basic embedded styles.
                    Do not include any explanations, introductions, extra phrases, or markdown formatting —
                    only raw HTML code that can be copied and run in a browser. Do not add markdown such as ``` html


                    """);
        } else {
            promptBuilder
                    .append("""
                            You are a news summariser. " +
                            Summarize the top global news stories from today in a concise and informative way.
                            Focus on major events, political developments, economic updates, and major technology or science breakthroughs.
                            Keep the summary clear, objective, and easy to read, like a daily news brief.
                            Do not preface the summary with introductions, phrases like 'Here is the summary,' or any commentary.
                            Return only the summary text itself, nothing else.


                            """);
        }
        promptBuilder.append(newsApiResponse.toString());
        return promptBuilder.toString();
    }

}