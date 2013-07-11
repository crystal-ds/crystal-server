/**
 * 
 */
package org.mitre.crystal.service.WorkSpace.impl;

import java.util.List;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.WorkSpace;
import org.mitre.crystal.repository.WorkSpaceRepository;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.WorkSpaceService;
import org.mitre.crystal.service.batchJob.impl.BatchJobServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service("WorkSpaceService")
public class WorkSpaceServiceImpl implements WorkSpaceService {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#createWorkSpace(java.lang.Long, java.lang.Long, java.util.List)
	 */
	@Autowired
	private BatchJobService bjs;
	
	@Autowired
	private WorkSpaceRepository wsr;
	
	
	final Logger log = LoggerFactory.getLogger(WorkSpaceServiceImpl.class);
	
	
	@Override
	public WorkSpace createWorkSpace(Long batchJobID) {
		log.info("Createing new workspace off BatchJobID " + batchJobID);
		BatchJob bj = bjs.getBatchJob(batchJobID);
		WorkSpace ws = new WorkSpace();
		ws.setBatchJobID(bj.getId());
		ws.setInstances(bj.getInstances());
		ws.setModelID(bj.getModelID());
		
		WorkSpace saved = wsr.save(ws);
		return saved;

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#getWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace getWorkSpace(Long ID) {
		return wsr.getWorkSpace( ID);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#updateWorkSpace(java.util.List)
	 */
	@Override
	public WorkSpace updateWorkSpace(WorkSpace ws) {
		WorkSpace myWorkSpace = wsr.getWorkSpace(ws.getWorkSpaceID());
		myWorkSpace.setInstances(ws.getInstances());
		return myWorkSpace;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#restoreWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace restoreWorkSpace(WorkSpace ws) {
		BatchJob bj = bjs.getBatchJob(ws.getBatchJobID());
		ws.setInstances(bj.getInstances());
		WorkSpace saved = wsr.save(ws);
		return saved;
		
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#deleteWorkSpace(java.lang.Long)
	 */
	@Override
	public void deleteWorkSpace(WorkSpace ws) {
		wsr.delete(ws);
	}

}
