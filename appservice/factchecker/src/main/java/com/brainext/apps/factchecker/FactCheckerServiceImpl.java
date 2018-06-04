package com.brainext.apps.factchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainext.apps.factchecker.kb.Entity;
import com.brainext.apps.factchecker.kb.KnowledgeBaseService;
import com.brainext.apps.factchecker.nlp.NlpService;
import com.brainext.apps.factchecker.nlp.Relation;

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
	public List<CheckedRelation> validate(String text) throws FactCheckerException {
		List<CheckedRelation> checkedRelations = new ArrayList<>();

		List<Relation> relations = nlpService.getRelations(text);

		for (Relation relation : relations) {
			/*
			 * TODO - have a formal representation for a subject and its
			 * objects rather than assume the subject is the first entity.
			 */
			List<String> entities = relation.getEntities();
			String subjectEntityId = entities.get(0);
			List<String> objectEntityIds = entities.subList(1, entities.size());

			Entity entity = kbService.getEntity(subjectEntityId);
			if (entity != null) {
				Set<String> propValues = entity.getStringProperty(relation.getType());

				if (propValues != null && propValues.containsAll(objectEntityIds)) {
					checkedRelations.add(new CheckedRelation(relation, true));
				} else {
					checkedRelations.add(new CheckedRelation(relation, false));
				}
			}
		}
		
		return checkedRelations;
	}
}
