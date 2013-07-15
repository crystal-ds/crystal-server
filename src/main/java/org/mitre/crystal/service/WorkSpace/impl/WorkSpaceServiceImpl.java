/**
 * 
 */
package org.mitre.crystal.service.WorkSpace.impl;

import java.util.ArrayList;
import java.util.List;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.WorkSpace;
import org.mitre.crystal.repository.WorkSpaceRepository;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ScoringBatchJobService;
import org.mitre.crystal.service.WorkSpaceService;
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
	private ScoringBatchJobService sbs;
	@Autowired
	private WorkSpaceRepository wsr;
	final Logger log = LoggerFactory.getLogger(WorkSpaceServiceImpl.class);
	
	
	@Override
	public WorkSpace createWorkSpace(Long batchJobID) {
		log.info("Createing new workspace off BatchJobID " + batchJobID);
		BatchJob bj = bjs.getBatchJob(batchJobID);
		SMBatchJob sbj = sbs.getScore(batchJobID);
		WorkSpace ws = new WorkSpace();
		ws.setBatchJob(bj);
		ws.setSmbj(sbj);
		ws.setOffMask(new ArrayList<ModelRunInstance>());		
		WorkSpace saved = wsr.save(ws);
		return saved;

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#getWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace getWorkSpace(Long ID) {
		log.info("Getting work Space with ID" + ID);
		return wsr.getWorkSpace( ID);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#updateWorkSpace(java.util.List)
	 */
	@Override
	public WorkSpace updateWorkSpace(Long workSpaceID, List<ModelRunInstance> mask) {
		log.info("updating workspace with id" + workSpaceID);
		WorkSpace myWorkSpace = wsr.getWorkSpace(workSpaceID);
		myWorkSpace.setOffMask(mask);
		wsr.save(myWorkSpace);
		return myWorkSpace;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#restoreWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace restoreWorkSpace(WorkSpace ws) {
		WorkSpace update = wsr.getWorkSpace(ws.getWorkSpaceID());
		update.setOffMask(new ArrayList<ModelRunInstance>());
		wsr.save(ws);
		return update;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#deleteWorkSpace(java.lang.Long)
	 */
	@Override
	public void deleteWorkSpace(WorkSpace ws) {
		wsr.delete(ws);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#updateWorkSpaceWithLong(java.lang.Long, java.util.List)
	 */
	@Override
	public WorkSpace updateWorkSpaceWithLong(Long workSpaceID, List<Long> mask) {
		log.info("updating workspace with id" + workSpaceID);
		WorkSpace myWorkSpace = wsr.getWorkSpace(workSpaceID);
		ArrayList<ModelRunInstance> newMask = new ArrayList<ModelRunInstance>();
		List<ModelRunInstance> fullList = myWorkSpace.getBatchJob().getInstances();
		for (ModelRunInstance modelRunInstance : fullList) {
			if(mask.contains(modelRunInstance.getId())){
				newMask.add(modelRunInstance);
			}
		}
		myWorkSpace.setOffMask(newMask);		
		wsr.save(myWorkSpace);
		return myWorkSpace;
	}

}
