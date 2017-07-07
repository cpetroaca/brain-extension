package com.brainext.apps.factchecker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainext.core.CoreServiceException;
import com.brainext.core.nlp.NlpService;
import com.brainext.core.nlp.Relation;

/**
 * Handles fact checking activities
 * 
 * @author cpetroaca
 *
 */
@Service
class FactCheckerServiceImpl implements FactCheckerService {
	@Autowired
	private NlpService nlpService;

	@Override
	public List<CheckedStatement> validateStatements(String text) throws FactCheckerException {
		List<Relation> relations = null;
		try {
			relations = nlpService.getRelations(text);
		} catch (CoreServiceException e) {
			throw new FactCheckerException(e);
		}

		List<CheckedStatement> statements = new ArrayList<>();
		statements.add(new CheckedStatement(1, true, "Albert Einstein was a professor at University of Zurich."));

		return statements;
	}
}
