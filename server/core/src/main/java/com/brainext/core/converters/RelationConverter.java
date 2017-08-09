package com.brainext.core.converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.brainext.core.nlp.Relation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom converter for the list of Relations
 * 
 * @author cpetroaca
 *
 */
public class RelationConverter extends AbstractConverter<List<Relation>> {
	private static final String W3_ORG = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	private static final String ID = "@id";
	private static final String VALUE = "@value";
	private static final String ENHANCEMENT_TYPE = "http://purl.org/dc/terms/type";
	private static final String RELATION_ENHANCEMENT = W3_ORG + "relation";
	private static final String ENTITY_TAG = W3_ORG + "entity";
	private static final String RELATION_TYPE_TAG = W3_ORG + "relation-type";
	private static final String CONFIDENCE_TAG = W3_ORG + "confidence-level";

	@Override
	public List<Relation> fromJson(JsonParser parser) throws IOException {
		List<Relation> relations = new ArrayList<>();

		String relationType = null;
		double confidence = 0;
		List<String> entities = new ArrayList<>();
		JsonNode enhancementNodes = parser.readValueAsTree();

		for (JsonNode enhancementNode : enhancementNodes) {
			boolean foundRelation = false;
			JsonNode enhancementTypeNodes = enhancementNode.get(ENHANCEMENT_TYPE);
			for (JsonNode enhancementTypeNode : enhancementTypeNodes) {
				String enhTypeString = enhancementTypeNode.get(ID).asText();
				if (enhTypeString.equals(RELATION_ENHANCEMENT)) {
					foundRelation = true;
					break;
				}
			}

			if (foundRelation) {
				JsonNode relationTypeNodes = enhancementNode.get(RELATION_TYPE_TAG);
				for (JsonNode relationTypeNode : relationTypeNodes) {
					relationType = relationTypeNode.get(ID).asText();
				}

				JsonNode confidenceNodes = enhancementNode.get(CONFIDENCE_TAG);
				for (JsonNode confidenceNode : confidenceNodes) {
					confidence = confidenceNode.get(VALUE).asDouble();
				}

				JsonNode entityNodes = enhancementNode.get(ENTITY_TAG);
				for (JsonNode entityNode : entityNodes) {
					entities.add(entityNode.get(ID).asText());
				}

				relations.add(new Relation(relationType, confidence, entities));
			}
		}

		return relations;
	}

}
