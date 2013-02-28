/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobRunStatus;
import org.mitre.crystal.model.ModelRunInputValues;

/**
 * @author tmlewis
 *
 */
public class ModelRunService {
	
	
	public long  createModelRunInstance(ModelRunInputValues input, long ModelId){
		//generates list of inputs for exploritory mdoeling run
		//does error checking on inputs
		//generates unique id for batch job
		//generates status "NOT_STARTED"
		//returns Batchjob id
	
	}
	public BatchJob getModelRunInstance(long batchJobId){
		
	}
	public BatchJobRunStatus runModelRunInstance(long batchJobID){
		
	}
	public void deleteBatchJob(long batchJobId){
		
	}

}

