package com.brainext.core.nlp;

/**
 * Entity transfer object from NLP service endpoint
 * 
 * @author cpetroaca
 *
 */
public class EntityDto {
	/**
	 * Entity text
	 */
	private String text;

	/**
	 * Entity type
	 */
	private String type;

	public EntityDto() {
	}

	public EntityDto(String text, String type) {
		if (text == null || text.isEmpty()) {
			throw new IllegalArgumentException("text cannot be null or empty");
		}

		if (type == null || type.isEmpty()) {
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

		EntityDto other = (EntityDto) obj;

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
