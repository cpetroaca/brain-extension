package com.brainext.apps.factchecker.nlp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.apps.factchecker.FactCheckerException;
import com.brainext.apps.factchecker.converters.RelationConverter;
import com.brainext.core.config.ConfigService;

/**
 * Nlp Service implementation which uses Apache Stanbol for NLP tasks
 * 
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
	public List<Relation> getRelations(String text) throws FactCheckerException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.set("Content-Type", "text/plain");

		HttpEntity<String> entity = new HttpEntity<>(text, headers);
		String response = restTemplate.postForObject(configService.getNlpServerUrl() + RELATION_EXTRACTION_ENDPOINT,
				entity, String.class);

		try {
			return new RelationConverter().parseJson(response);
		} catch (IOException e) {
			throw new FactCheckerException(e);
		}
	}
}
