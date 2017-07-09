package com.brainext.apps.factchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainext.core.CoreServiceException;
import com.brainext.core.kb.Entity;
import com.brainext.core.kb.KnowledgeBaseService;
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
	
	@Autowired
	private KnowledgeBaseService kbService;

	@Override
	public List<CheckedStatement> validateStatements(String text) throws FactCheckerException {
		try {
			List<Relation> relations = nlpService.getRelations(text);
			
			for (Relation relation : relations) {
				for (String entityId : relation.getEntities()) {
					Entity entity = kbService.getEntity(entityId);
					if (entity != null) {
						Set<String> propValues = entity.getStringProperty(relation.getType());
						if (propValues != null) {
							propValues.size();
						}
					}
				}
			}
		} catch (CoreServiceException e) {
			throw new FactCheckerException(e);
		}

		List<CheckedStatement> statements = new ArrayList<>();
		statements.add(new CheckedStatement(1, true, "Albert Einstein was a professor at University of Zurich."));

		return statements;
	}
}
