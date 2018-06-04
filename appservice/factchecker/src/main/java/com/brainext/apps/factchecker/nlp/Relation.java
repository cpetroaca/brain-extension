package com.brainext.apps.factchecker.nlp;

import java.util.List;

/**
 * A semantic relation between two or more entities
 * 
 * @author cpetroaca
 *
 */
public class Relation {
	/**
	 * The type of relation
	 */
	private String type;

	/**
	 * The confidence with which we know it is this type of relation
	 */
	private double confidence;

	/**
	 * The set of entities participating in the relation
	 */
	private List<String> entities;

	public Relation(String type, double confidence, List<String> entities) {
		if (type == null) {
			throw new IllegalArgumentException("type cannot be null");
		}

		if (entities == null || entities.isEmpty()) {
			throw new IllegalArgumentException("entities cannot be null or empty");
		}

		this.type = type;
		this.confidence = confidence;
		this.entities = entities;
	}

	/**
	 * 
	 * @return the relation type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @return the relation confidence
	 */
	public double getConfidence() {
		return confidence;
	}

	/**
	 * 
	 * @return the set of entities participating in the relation
	 */
	public List<String> getEntities() {
		return entities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type.hashCode();
		result = prime * result + Double.valueOf(confidence).hashCode();
		result = prime * result + entities.hashCode();
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

		Relation other = (Relation) obj;

		return (type.equals(other.type)) && (confidence == other.confidence) && (entities.equals(other.entities));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type=");
		sb.append(type);
		sb.append(", ");
		sb.append("Confidence=");
		sb.append(confidence);
		sb.append(", ");
		sb.append("Entities=");
		sb.append(entities);

		return sb.toString();
	}
}
