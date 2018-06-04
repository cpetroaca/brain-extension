package com.brainext.apps.eventgraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class ScrapingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingService.class);
	
	@Scheduled(fixedRate = 5000)
    public void scrapeEvents() {
		LOGGER.info("Scraping events");
    }
}
