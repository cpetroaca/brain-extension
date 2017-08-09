package com.brainext.apps.factchecker;

import java.util.List;

/**
 * Performs business logic wrt checking veridity of statements
 * 
 * @author cpetroaca
 *
 */
public interface FactCheckerService {
	/**
	 * Validates whether the given text contains statements which are true or
	 * false
	 * 
	 * @param text
	 * @return
	 */
	public List<CheckedRelation> validate(String text) throws FactCheckerException;
}
