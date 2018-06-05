package com.brainext.core.nlp;

/**
 * Named entity
 * 
 * @author cpetroaca
 *
 */
public class Entity {
	/**
	 * Entity text
	 */
	private String text;

	/**
	 * Entity type
	 */
	private EntityType type;

	public Entity() {
	}
	
	public Entity(String text, EntityType type) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("text cannot be null or empty");
		}

		if (type == null) {
			throw new IllegalArgumentException("type cannot be null or empty");
		}

		this.text = text;
		this.type = type;
	}

	/**
	 * 
	 * @return get text of entity
	 */
	public String getText() {
		return text;
	}

	/**
	 * 
	 * @return the entity type
	 */
	public EntityType getType() {
		return type;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + text.hashCode();
		result = prime * result + type.hashCode();
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

		return (text.equals(other.text)) && (type.equals(other.type));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Text=");
		sb.append(text);
		sb.append(", ");
		sb.append("Type=");
		sb.append(type);

		return sb.toString();
	}
}
