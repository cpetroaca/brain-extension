package com.brainext.core.kb;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PreDestroy;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainext.core.config.ConfigService;

@Service
class JenaKnowledgeBaseService implements KnowledgeBaseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JenaKnowledgeBaseService.class);

	@Autowired
	private ConfigService configService;

	private CloseableHttpClient httpClient;

	public JenaKnowledgeBaseService() {
		httpClient = HttpClients.createMinimal();
	}

	@Override
	public Entity getEntity(String id) {
		return null;
	}

	@Override
	public Collection<Entity> getEntities() {
		QueryExecution q = QueryExecutionFactory.sparqlService(configService.getKnowledgeBaseServerUrl(),
				"SELECT ?subject ?predicate ?object WHERE {?subject ?predicate ?object}", httpClient);

		ResultSet results = q.execSelect();
		Map<String, Entity> entities = new HashMap<>();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			String subject = soln.get("subject").asResource().getURI();
			String predicate = soln.get("predicate").asResource().getURI();
			String object = soln.get("object").asResource().getURI();

			Entity entity = entities.get(subject);
			if (entity == null) {
				Map<String, Set<Entity>> relations = new HashMap<>();
				Set<Entity> relationEntities = new HashSet<>();
				relationEntities.add(new Entity(object));
				relations.put(predicate, relationEntities);
				entities.put(subject, new Entity(subject, relations));
			} else {
				Map<String, Set<Entity>> relations = entity.getRelations();
				if (!relations.containsKey(predicate)) {
					Set<Entity> relationEntities = new HashSet<>();
					relationEntities.add(new Entity(object));
					relations.put(predicate, relationEntities);
					entities.put(subject, new Entity(subject, relations));
				} else {
					Set<Entity> relationEntities = relations.get(predicate);
					relationEntities.add(new Entity(object));
					relations.put(predicate, relationEntities);
					entities.put(subject, new Entity(subject, relations));
				}
			}
		}

		return entities.values();
	}

	@PreDestroy
	public void cleanup() {
		try {
			httpClient.close();
		} catch (IOException e) {
			LOGGER.error("Got exception while closing httpClient: " + e);
		}
	}
}
