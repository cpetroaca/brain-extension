package com.brainext.apps.factchecker.kb;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.apps.factchecker.FactCheckerException;
import com.brainext.apps.factchecker.converters.EntityConverter;
import com.brainext.core.config.ConfigService;

@Service
class KnowledgeBaseServiceImpl implements KnowledgeBaseService {
	private static final String DBPEDIA_FACTS_ENDPOINT = "entityhub/site/dbpediafacts/";
	private static final String ENTITY_QUERY = "entity?id=";

	/**
	 * Configuration Service
	 */
	@Autowired
	private ConfigService configService;

	/**
	 * Spring REST template used to make http calls
	 */
	private RestTemplate restTemplate;

	public KnowledgeBaseServiceImpl() {
		restTemplate = new RestTemplate();
	}

	@Override
	public Entity getEntity(String id) throws FactCheckerException {
		StringBuilder url = new StringBuilder();
		url.append(configService.getNlpServerUrl());
		url.append(DBPEDIA_FACTS_ENDPOINT);
		url.append(ENTITY_QUERY);
		url.append(id);

		String response = restTemplate.getForObject(url.toString(), String.class);

		try {
			return new EntityConverter().parseJson(response);
		} catch (IOException e) {
			throw new FactCheckerException(e);
		}
	}
}
