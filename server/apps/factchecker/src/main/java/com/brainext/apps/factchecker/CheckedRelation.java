package com.brainext.apps.factchecker;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.brainext.core.nlp.Relation;

/**
 * A relation which was checked for truthfulness
 * 
 * @author cpetroaca
 *
 */
public class CheckedRelation {
	/**
	 * The type of relation
	 */
	private String type;

	/**
	 * The set of entities participating in the relation
	 */
	private List<String> entities;

	/**
	 * Whether this relation is true or not
	 */
	private boolean isFact;

	public CheckedRelation(Relation relation, boolean isFact) {
		if (relation == null) {
			throw new IllegalArgumentException("relation cannot be null");
		}

		this.type = relation.getType();
		this.entities = relation.getEntities();
		this.isFact = isFact;
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
	 * @return the set of entities participating in the relation
	 */
	public List<String> getEntities() {
		return entities;
	}

	@JsonProperty("isFact")
	public boolean isFact() {
		return isFact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type.hashCode();
		result = prime * result + entities.hashCode();
		result = prime * result + (isFact ? 1 : 0);
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

		CheckedRelation other = (CheckedRelation) obj;

		return (type.equals(other.type)) && (entities.equals(other.entities)) && (isFact == other.isFact);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type=");
		sb.append(type);
		sb.append(", ");
		sb.append(", ");
		sb.append("Entities=");
		sb.append(entities);
		sb.append("IsFact=");
		sb.append(isFact);

		return sb.toString();
	}
}
