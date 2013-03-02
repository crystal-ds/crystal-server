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

/**
 * @author tmlewis
 *
 */
public class BatchJobService {
	
	
	public long  createBatchJob(ExploratoryModelingInputSpecification input, long modelId){
		BatchJob bj = new BatchJob();
		bj.setModelId(modelId);
		bj.setInputs(createInputsForBatchJob(input));
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
	public BatchJobRunStatus runBatchJob(long batchJobID){
		BatchJob bj = getBatchJob(batchJobId);
		List l = bj.getInputs();
		for (Object object : l) {
			
		}
	}
	public void deleteBatchJob(long batchJobId){
		
	}

	private List<ModelRunInputValues> createInputsForBatchJob(ExploratoryModelingInputSpecification input){
		//TODO does error checking on inputs
		//generates list of inputs for exploritory mdoeling run
	}
}
