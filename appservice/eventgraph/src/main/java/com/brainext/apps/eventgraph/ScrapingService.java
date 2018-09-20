package com.brainext.apps.eventgraph;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.brainext.core.kb.KnowledgeBaseService;
import com.brainext.core.kb.Relation;
import com.brainext.core.nlp.NlpService;

/**
 * Service performing news site scraping
 * 
 * @author cpetroaca
 *
 */
public abstract class ScrapingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingService.class);

	@Autowired
	private NlpService nlpService;

	@Autowired
	private KnowledgeBaseService kbService;

	private RestTemplate restTemplate;

	public ScrapingService() {
		restTemplate = new RestTemplate();
	}

	@Scheduled(fixedRate = 5000)
	public void scrape() {
		String eventsText = scrapeEvents();
		LOGGER.info("Scraped events text=" + eventsText);
		List<Relation> relations = nlpService.getRelations(eventsText);
		
		kbService.insertRelations(relations);
	}

	/**
	 * Do the actual custom scraping
	 * 
	 * @return a String containing all the scraped text
	 */
	protected abstract String scrapeEvents();

	protected RestTemplate getRestTemplate() {
		return restTemplate;
	}
}
