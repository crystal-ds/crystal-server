/**
 * 
 */
package org.mitre.crystal.service;

import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 *
 */
public interface BatchJobService {

	public abstract BatchJob createBatchJob(RunnableModel model,
			Map<String,String> vals);

	public abstract BatchJob getBatchJob(long batchJobId);

	public abstract BatchJobStatus runBatchJob(long batchJobID);

	public abstract BatchJobStatus createAndRunBatchJob(
			RunnableModel model, Map<String,String> input);


	/**
	 * @param batchJob
	 */
	void deleteBatchJob(BatchJob batchJob);

}