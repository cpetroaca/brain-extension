package com.brainext.apps.factchecker.kb;

import com.brainext.apps.factchecker.FactCheckerException;

/**
 * Service which accesses knowledge base entities
 * 
 * @author cpetroaca
 *
 */
public interface KnowledgeBaseService {
	public Entity getEntity(String id) throws FactCheckerException;
}
