package com.brainext.apps.factchecker.nlp;

import java.util.List;

import com.brainext.apps.factchecker.FactCheckerException;

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
	public List<Relation> getRelations(String text) throws FactCheckerException;
}
