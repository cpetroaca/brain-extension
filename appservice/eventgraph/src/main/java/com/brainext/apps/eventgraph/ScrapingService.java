package com.brainext.apps.eventgraph;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.brainext.core.kb.Entity;
import com.brainext.core.kb.KnowledgeBaseService;
import com.brainext.core.nlp.NlpService;
import com.brainext.core.nlp.RelationDto;

@Component
class ScrapingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingService.class);
	
	@Autowired
	private NlpService nlpService;
	
	@Autowired
	private KnowledgeBaseService kbService;
	
	@Scheduled(fixedRate = 5000)
    public void scrapeEvents() {
		List<RelationDto> relations = nlpService.getRelations("Barack Obama visited China. Microsoft bought Intel.");
		LOGGER.info("Scraped events:");
		for (RelationDto relation : relations) {
			LOGGER.info("Event: " + relation.toString());
		}
		
		Collection<Entity> entities = kbService.getEntities();
		for (Entity entity : entities) {
			LOGGER.info("Entity: " + entity.toString());
		}
    }
}
