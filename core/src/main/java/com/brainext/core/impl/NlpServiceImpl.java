package com.brainext.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.core.ConfigService;
import com.brainext.core.NlpService;

/**
 * Nlp Service implementation which uses Apache Stanbol for NLP tasks
 * @author cpetroaca
 *
 */
@Service
class NlpServiceImpl implements NlpService {
	private static final String RELATION_EXTRACTION_ENDPOINT = "enhancer/chain/relationextraction";
	
	/**
	 * Configuration Service
	 */
	@Autowired
	private ConfigService configService;
	
	/**
	 * Spring REST template used to make http calls
	 */
	private RestTemplate restTemplate;
	
	public NlpServiceImpl() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public String getRelations(String text) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "text/plain");
		
		HttpEntity<String> entity = new HttpEntity<>(text, headers);
		String response = restTemplate.postForObject(configService.getNlpServerUrl() + RELATION_EXTRACTION_ENDPOINT, entity, String.class);
		
		return response;
	}
}
