/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmlewis
 *
 */
public class BatchJobService {
	
	//TODO Database integration
	
	final Logger log = LoggerFactory.getLogger(BatchJobService.class);
	public long  createBatchJob(ModelSpecification model, ModelRunInputValues vals){
		log.debug("Creating Batch Job");
		BatchJob bj = new BatchJob();
		bj.setModelId(model.getId());
		bj.setRuns(createInputsForBatchJob(vals));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		//TODO setID with automagic number from db
		bj.setId(null);
		log.info("batch Job created with ID" + bj.getId());
		return bj.getId();
	
	}
	public BatchJob getBatchJob(long batchJobId){
		BatchJob bj = new BatchJob();
		bj.setId(batchJobId);
		//TODO JPA magic!
		return bj;
	}
	public BatchJobStatus runBatchJob(long batchJobID){
		log.info("Checking status for batch job" + batchJobID);
		BatchJob bj = getBatchJob(batchJobID);
		List<ModelRunInstance> runs = bj.getRuns();
		for (ModelRunInstance run : runs) {
			//TODO figure out how to calculate status
		}

		return BatchJobStatus.UNKNOWN;
	}
		
	public BatchJobStatus createAndRunBatchJob(ModelSpecification model, ModelRunInputValues input){
		log.info("create And run BatchJob");
		return runBatchJob(createBatchJob(model, input));
		
	
	}
	public void deleteBatchJob(long batchJobId){
		log.info("removing batch job " + batchJobId); 
		
	}

	private List<ModelRunInstance> createInputsForBatchJob(ModelRunInputValues input){
		log.debug("Creating inputs for batch Job");
		//TODO do error checking on inputs
		//generates list of Model  for exploritory mdoeling run
		log.debug("Finished creating inputs for batch job");
		return null;
	}
}
