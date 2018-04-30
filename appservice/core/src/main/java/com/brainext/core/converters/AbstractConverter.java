package com.brainext.core.converters;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base class for a json converter
 * 
 * @author cpetroaca
 *
 * @param <T>
 */
public abstract class AbstractConverter<T> {
	/**
	 * Parses the given json string to the configured Type
	 * 
	 * @param jsonString
	 * @return
	 * @throws JsonParseException
	 * @throws IOException
	 */
	public T parseJson(String jsonString) throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonParser parser = mapper.getFactory().createParser(jsonString);

		return fromJson(parser);
	}

	/**
	 * Custom parse method
	 * 
	 * @param parser
	 * @return
	 * @throws IOException
	 */
	protected abstract T fromJson(JsonParser parser) throws IOException;
}
