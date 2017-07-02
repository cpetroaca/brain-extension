package com.brainext.core;

/**
 * Service that performs Natural Language Processing tasks
 * 
 * @author cpetroaca
 *
 */
public interface NlpService {
	/**
	 * Get semantic relations between two or more entities in the given text
	 * 
	 * @param text
	 * @return
	 */
	public String getRelations(String text);
}
