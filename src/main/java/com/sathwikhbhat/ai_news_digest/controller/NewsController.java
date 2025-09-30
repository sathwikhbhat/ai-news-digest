package com.sathwikhbhat.ai_news_digest.controller;

import com.sathwikhbhat.ai_news_digest.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "summary", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getTopHeadlines() {
        return newsService.getTopHeadlines(false)
                .candidates().getFirst().content().parts().getFirst().text();
    }

    @GetMapping(value = "render", produces = MediaType.TEXT_HTML_VALUE)
    public String renderNewsDigest() {
        return newsService.getTopHeadlines(true)
                .candidates().getFirst().content().parts().getFirst().text();
    }

    @DeleteMapping("cache")
    public String clearCache() {
        newsService.clearCache();
        return "Cache cleared successfully";
    }

}