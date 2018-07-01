package com.brainext.core.kb;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * An Entity stored in a Knowledge base
 * 
 * @author cpetroaca
 *
 */
public class Entity {
	/**
	 * Value of the entity
	 */
	private String value;

	/**
	 * Map of entity relations. Key is id of property and value is a set of
	 * property values
	 */
	private Map<String, Set<Entity>> relations;

	public Entity(String value) {
		this(value, Collections.emptyMap());
	}

	public Entity(String value, Map<String, Set<Entity>> relations) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("value cannot be null or empty");
		}

		this.value = value;
		this.relations = relations;
	}

	/**
	 * 
	 * @return get value of entity
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Get relations
	 * 
	 * @param propertyName
	 * @return
	 */
	public Map<String, Set<Entity>> getRelations() {
		return new HashMap<>(relations);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value.hashCode();
		result = prime * result + relations.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Entity other = (Entity) obj;

		return (value.equals(other.value)) && (relations.equals(other.relations));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Value=");
		sb.append(value);
		sb.append(", ");
		sb.append("Relations=");
		sb.append(relations);

		return sb.toString();
	}
}
