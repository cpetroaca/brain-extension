package com.brainext.apps.eventgraph.techcrunch;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.brainext.apps.eventgraph.ScrapingService;

/**
 * Scraping service for Techcrunch news
 * @author cpetroaca
 *
 */
@Component
class TechcrunchScrapingService extends ScrapingService {
	@Value("${techcrunch.scrape.url}")
	private String scrapingUrl;

	@Value("${techcrunch.api.key}")
	private String apiKey;

	@Override
	protected String scrapeEvents() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("X-Api-Key", apiKey);
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		ResponseEntity<ArticlesResponse> response = getRestTemplate().exchange(scrapingUrl, HttpMethod.GET, entity,
				ArticlesResponse.class);

		ArticlesResponse articleResponse = response.getBody();
		StringBuilder sentences = new StringBuilder();
		articleResponse.getArticles().stream().forEach((article) -> sentences.append(article.getTitle() + "."));

		return sentences.toString();
	}
}
