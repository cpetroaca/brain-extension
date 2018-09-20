package com.brainext.core.kb;

/**
 * Entity object
 * 
 * @author cpetroaca
 *
 */
public class Entity {
	public static final String NO_TYPE = "";
	
	/**
	 * Entity text
	 */
	private String text;

	/**
	 * Entity type
	 */
	private String type;

	public Entity() {
	}

	public Entity(String text) {
		this(text, NO_TYPE);
	}
	
	public Entity(String text, String type) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("text cannot be null or empty");
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
	public String getType() {
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
