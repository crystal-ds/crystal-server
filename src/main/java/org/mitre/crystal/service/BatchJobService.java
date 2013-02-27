/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.ModelRunInputValues;

/**
 * @author tmlewis
 *
 */
public class BatchJobService {
	
	
	public long  createBatchJob(ModelRunInputValues input, long ModelId){
		//generates list of inputs for exploritory mdoeling run
		//does error checking on inputs
		//generates unique id for batch job
		//generates status "NOT_STARTED"
		//returns Batchjob id
	
	}
	public BatchJob getBatchJob(long batchJobId){
		
	}
	public BatchJobRunStatus runBatchJob(long batchJobID){
		
	}

}
