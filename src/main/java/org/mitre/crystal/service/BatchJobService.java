/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobRunStatus;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ExploratoryModelingInputSpecification;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelRunInstance;

/**
 * @author tmlewis
 *
 */
public class BatchJobService {
	
	
	public long  createBatchJob(ExploratoryModelingInputSpecification input, long modelId){
		BatchJob bj = new BatchJob();
		bj.setModelId(modelId);
		bj.setRuns(createInputsForBatchJob(input));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		//TODO setID with automagic number from db
		bj.setId(null);
		return bj.getId();
	
	}
	public BatchJob getBatchJob(long batchJobId){
		BatchJob bj = new BatchJob();
		bj.setId(batchJobId);
		//TODO JPA magic!
		return bj;
	}
	public BatchJobStatus runBatchJob(long batchJobID){
		BatchJob bj = getBatchJob(batchJobID);
		List<ModelRunInstance> runs = bj.getRuns();
		for (ModelRunInstance run : runs) {
			//TODO figure out status
		}

		return BatchJobStatus.UNKNOWN;
	}
		
	public BatchJobStatus createAndRunBatchJob(ExploratoryModelingInputSpecification input, long modelId){
		return runBatchJob(createBatchJob(input, modelId));
		
	
	}
	public void deleteBatchJob(long batchJobId){
		
	}

	private List<ModelRunInstance> createInputsForBatchJob(ExploratoryModelingInputSpecification input){
		//TODO does error checking on inputs
		//generates list of inputs for exploritory mdoeling run
	}
}
