package com.brainext.core.nlp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.core.config.ConfigService;

/**
 * Nlp Service implementation which uses Apache Stanbol for NLP tasks
 * 
 * @author cpetroaca
 *
 */
@Service
class NlpServiceImpl implements NlpService {
	private static final String RELATION_EXTRACTION_ENDPOINT = "relations";

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
	public List<Relation> getRelations(String text) {
		Map<String, String> vars = new HashMap<>();

		Relation[] response = restTemplate.getForObject(configService.getNlpServerUrl() + RELATION_EXTRACTION_ENDPOINT,
				Relation[].class, vars);

		return Arrays.asList(response);
	}
}
