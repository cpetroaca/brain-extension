package com.brainext.core.converters;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.brainext.core.kb.Entity;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;

public class EntityConverter extends AbstractConverter<Entity> {
	private static final String ID = "id";
	private static final String VALUE = "value";
	private static final String PROPERTY_TYPE = "type";
	private static final String PROPERTY_DATATYPE = "xsd:datatype";
	private static final String REPRESENTATION = "representation";

	@Override
	protected Entity fromJson(JsonParser parser) throws IOException {
		JsonNode node = parser.readValueAsTree();

		String id = node.get(ID).asText();
		Map<String, Set<? extends Object>> properties = new HashMap<>();

		JsonNode representation = node.get(REPRESENTATION);
		Iterator<Entry<String, JsonNode>> fields = representation.fields();
		while (fields.hasNext()) {
			Entry<String, JsonNode> field = fields.next();
			String fieldId = field.getKey();

			/*
			 * Ignore Id, we already have it
			 */
			if (fieldId.equals(ID))
				continue;

			Set<Object> propertyValues = new HashSet<>();
			for (JsonNode propertyNode : field.getValue()) {
				PropertyType propType = PropertyType.valueOf(propertyNode.get(PROPERTY_TYPE).asText().toUpperCase());

				if (propType == PropertyType.TEXT || propType == PropertyType.REFERENCE) {
					propertyValues.add(propertyNode.get(VALUE).asText());
				} else if (propType == PropertyType.VALUE) {
					PropertyDataType dataType = PropertyDataType.getValue(propertyNode.get(PROPERTY_DATATYPE).asText());

					if (dataType == PropertyDataType.FLOAT) {
						propertyValues.add(propertyNode.get(VALUE).asDouble());
					}
				}
			}

			properties.put(fieldId, propertyValues);
		}

		return new Entity(id, properties);
	}
}

enum PropertyDataType {
	FLOAT("xsd:float"),

	URI("xsd:anyURI");

	private String name;

	private PropertyDataType(String name) {
		this.name = name;
	}

	public static PropertyDataType getValue(String name) {
		for (PropertyDataType dataType : values()) {
			if (dataType.name.equals(name))
				return dataType;
		}

		return null;
	}
}

enum PropertyType {
	VALUE,

	TEXT,

	REFERENCE;
}
