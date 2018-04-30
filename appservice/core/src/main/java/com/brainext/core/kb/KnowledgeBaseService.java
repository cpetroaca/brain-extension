package com.brainext.core.kb;

import com.brainext.core.CoreServiceException;

/**
 * Service which accesses knowledge base entities
 * 
 * @author cpetroaca
 *
 */
public interface KnowledgeBaseService {
	public Entity getEntity(String id) throws CoreServiceException;
}
