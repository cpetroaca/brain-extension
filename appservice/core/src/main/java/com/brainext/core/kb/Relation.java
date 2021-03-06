package com.brainext.core.kb;

/**
 * Relation object
 * 
 * @author cpetroaca
 *
 */
public class Relation {
	/**
	 * The type of relation
	 */
	private String type;

	private Entity subj;

	private Entity obj;

	public Relation() {
	}

	public Relation(String type, Entity subj, Entity obj) {
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException("type cannot be null or empty");
		}

		if (subj == null) {
			throw new IllegalArgumentException("subj cannot be null");
		}

		if (obj == null) {
			throw new IllegalArgumentException("obj cannot be null");
		}

		this.type = type;
		this.subj = subj;
		this.obj = obj;
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
	 * @return relation subject
	 */
	public Entity getSubj() {
		return subj;
	}

	/**
	 * 
	 * @return relation object
	 */
	public Entity getObj() {
		return obj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + type.hashCode();
		result = prime * result + subj.hashCode();
		result = prime * result + obj.hashCode();
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

		return (type.equals(other.type)) && (subj.equals(other.subj)) && (obj.equals(other.obj));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Type=");
		sb.append(type);
		sb.append(", ");
		sb.append("Subj=[");
		sb.append(subj);
		sb.append("]");
		sb.append(", ");
		sb.append("Obj=[");
		sb.append(obj);
		sb.append("]");

		return sb.toString();
	}
}
