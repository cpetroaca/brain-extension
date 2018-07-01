package com.brainext.core.nlp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.core.config.ConfigService;

/**
 * Nlp Service implementation which uses Sophie endpoint
 * 
 * @author cpetroaca
 *
 */
@Service
class SophieNlpService implements NlpService {
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

	public SophieNlpService() {
		restTemplate = new RestTemplate();
	}

	@Override
	public List<RelationDto> getRelations(String text) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(configService.getNlpServerUrl());
		urlBuilder.append(RELATION_EXTRACTION_ENDPOINT);
		urlBuilder.append("?");
		urlBuilder.append("text=");
		urlBuilder.append(text);

		ResponseEntity<RelationDto[]> response = restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, entity,
				RelationDto[].class);

		return Arrays.asList(response.getBody());
	}
}
