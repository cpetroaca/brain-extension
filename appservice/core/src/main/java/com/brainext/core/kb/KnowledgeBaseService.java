package com.brainext.core.kb;

import java.util.Collection;

/**
 * Service which accesses knowledge base entities
 * 
 * @author cpetroaca
 *
 */
public interface KnowledgeBaseService {
	public Entity getEntity(String id);
	
	public Collection<Entity> getEntities();
	
	public void insertEntity(Entity entity);
}
