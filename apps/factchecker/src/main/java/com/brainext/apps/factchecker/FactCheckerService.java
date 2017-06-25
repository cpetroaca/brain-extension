package com.brainext.apps.factchecker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.brainext.apps.factchecker.datamodel.CheckedStatement;

/**
 * Performs business logic wrt checking veridity of statements
 * 
 * @author cpetroaca
 *
 */
@Service
public class FactCheckerService {
	@SuppressWarnings("unused")
	private RestTemplate restTemplate;

	/**
	 * Validates whether the given text contains statements which are true or
	 * false
	 * 
	 * @param text
	 * @return
	 */
	public List<CheckedStatement> validateStatements(String text) {
		List<CheckedStatement> checkedStatements = new ArrayList<>();
		checkedStatements.add(new CheckedStatement(1, true, "Albert Einstein works at University of Zurich"));

		return checkedStatements;
	}
}
