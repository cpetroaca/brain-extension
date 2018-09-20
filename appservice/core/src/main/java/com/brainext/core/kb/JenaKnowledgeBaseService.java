package com.brainext.core.kb;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.PreDestroy;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
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
	public Collection<Relation> getRelations() {
		QueryExecution q = QueryExecutionFactory.sparqlService(configService.getKnowledgeBaseServerUrl(),
				"SELECT ?subject ?predicate ?object WHERE {?subject ?predicate ?object}", httpClient);

		ResultSet results = q.execSelect();
		Collection<Relation> relations = Collections.emptyList();

		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			String subject = soln.get("subject").asResource().getURI();
			String predicate = soln.get("predicate").asResource().getURI();
			String object = soln.get("object").asResource().getURI();

			Entity subjectEntity = new Entity(subject);
			Entity objectEntity = new Entity(object);
			relations.add(new Relation(predicate, subjectEntity, objectEntity));
		}

		return relations;
	}

	@Override
	public void insertRelations(Collection<Relation> relations) {
		StringBuilder triplesString = new StringBuilder();
		
		for (Relation relation : relations) {
			triplesString.append("<");
			triplesString.append(relation.getSubj().getText().replace(" ", "_"));
			triplesString.append(">");
			triplesString.append(" ");
			triplesString.append("<");
			triplesString.append(relation.getType().replace(" ", "_"));
			triplesString.append(">");
			triplesString.append(" ");
			triplesString.append("<");
			triplesString.append(relation.getObj().getText().replace(" ", "_"));
			triplesString.append(">");
			triplesString.append(" .");
			triplesString.append("\n");
		}

		LOGGER.info("Insert query=" + triplesString.toString());
		UpdateRequest update = UpdateFactory.create("INSERT DATA { " + triplesString.toString() + "}");
		UpdateProcessor processor = UpdateExecutionFactory.createRemote(update,
				configService.getKnowledgeBaseServerUrl() + "/update");
		processor.execute();
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
