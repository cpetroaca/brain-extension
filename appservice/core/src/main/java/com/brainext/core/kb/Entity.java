package com.brainext.core.kb;

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
	 * Id of the entity
	 */
	private String id;

	/**
	 * Map of entity properties. Key is id of property and value is a set of
	 * property values
	 */
	private Map<String, Set<? extends Object>> properties;

	public Entity(String id, Map<String, Set<? extends Object>> properties) {
		if (id == null || id.isEmpty()) {
			throw new IllegalArgumentException("id cannot be null or empty");
		}

		if (properties == null || properties.isEmpty()) {
			throw new IllegalArgumentException("properties cannot be null or empty");
		}

		this.id = id;
		this.properties = properties;
	}

	/**
	 * 
	 * @return get id of entity
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get property values
	 * 
	 * @param propertyName
	 * @return
	 */
	public Set<? extends Object> getProperty(String propertyName) {
		return properties.get(propertyName);
	}

	/**
	 * Get property string values
	 * 
	 * @param propertyName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Set<String> getStringProperty(String propertyName) {
		return (Set<String>) properties.get(propertyName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
		result = prime * result + properties.hashCode();
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

		return (id.equals(other.id)) && (properties.equals(other.properties));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id=");
		sb.append(id);
		sb.append(", ");
		sb.append("Properties=");
		sb.append(properties);

		return sb.toString();
	}
}
