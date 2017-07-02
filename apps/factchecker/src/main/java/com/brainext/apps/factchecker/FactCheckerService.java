package com.brainext.apps.factchecker;

import java.util.List;

import com.brainext.apps.factchecker.datamodel.CheckedStatement;

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
	public List<CheckedStatement> validateStatements(String text);
}
