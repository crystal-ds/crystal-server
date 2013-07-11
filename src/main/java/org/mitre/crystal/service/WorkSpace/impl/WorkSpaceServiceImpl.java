/**
 * 
 */
package org.mitre.crystal.service.WorkSpace.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoreRunInstance;
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
		ws.setBatchJobID(bj.getId());
		
		Map<ModelRunInstance, ScoreRunInstance> m = new HashMap<ModelRunInstance, ScoreRunInstance>();
		List<ModelRunInstance> batchJobList = bj.getInstances();
		List<ScoreRunInstance> scoreList = sbj.getInstances();
		//TODO redo data structurs so this is more elegant
		for (ModelRunInstance modelRunInstance : batchJobList) {
			for (ScoreRunInstance scoreRunInstance : scoreList) {
				if (scoreRunInstance.getMriJobInstanceID() == modelRunInstance.getId()){
					m.put(modelRunInstance, scoreRunInstance);
				}
			}
		}
		ws.setInstances(m);

		
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
	public WorkSpace updateWorkSpace(WorkSpace ws) {
		log.info("updating workspace with id" + ws.getWorkSpaceID());
		WorkSpace myWorkSpace = wsr.getWorkSpace(ws.getWorkSpaceID());
		myWorkSpace.setInstances(ws.getInstances());
		wsr.save(myWorkSpace);
		return myWorkSpace;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.WorkSpaceService#restoreWorkSpace(java.lang.Long)
	 */
	@Override
	public WorkSpace restoreWorkSpace(WorkSpace ws) {
		createWorkSpace(ws.getBatchJobID());
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
