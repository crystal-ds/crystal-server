/**
 * 
 */
package org.mitre.crystal.repository.workspace.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.mitre.crystal.model.WorkSpace;
import org.mitre.crystal.repository.WorkSpaceRepository;
import org.mitre.crystal.repository.batchJob.impl.JPABatchJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tmlewis
 * JPA implementation of a Workspace Repo
 */
@Repository("jpaWorkSpaceRepository")
public class JPAWorkSpaceRepositoryImpl implements WorkSpaceRepository {

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);

	@PersistenceContext
	private EntityManager manager;

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.WorkSpaceRepository#save(org.mitre.crystal.model.WorkSpace)
	 */
	@Override
	@Transactional
	public WorkSpace save(WorkSpace ws) {
		if (ws.getWorkSpaceID() == null){
			//new
			manager.persist(ws);
			manager.flush();
			return ws;
		}else{
			final WorkSpace mergedWorkSpace = manager.merge(ws);
			manager.flush();
			return mergedWorkSpace;
		}
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.WorkSpaceRepository#getWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace getWorkSpace(Long ID) {
		return manager.find(WorkSpace.class, ID);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.WorkSpaceRepository#delete(org.mitre.crystal.model.WorkSpace)
	 */
	@Override
	@Transactional
	public void delete(WorkSpace ws) {
		final WorkSpace check = getWorkSpace(ws.getWorkSpaceID());
		if(check != null){     					
			manager.remove(check);
		}
	}
}
