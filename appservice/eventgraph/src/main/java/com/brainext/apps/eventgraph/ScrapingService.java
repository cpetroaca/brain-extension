package com.brainext.apps.eventgraph;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brainext.core.nlp.NlpService;
import com.brainext.core.nlp.Relation;

@Component
class ScrapingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingService.class);
	
	@Autowired
	private NlpService nlpService;
	
	@Scheduled(fixedRate = 5000)
    public void scrapeEvents() {
		List<Relation> relations = nlpService.getRelations("Barack Obama visited China. Microsoft bought Intel.");
		LOGGER.info("Scraped events:");
		for (Relation relation : relations) {
			LOGGER.info("Event: " + relation.toString());
		}
    }
}
