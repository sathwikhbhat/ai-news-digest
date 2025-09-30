package com.sathwikhbhat.ai_news_digest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AiNewsDigestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiNewsDigestApplication.class, args);
	}

}
