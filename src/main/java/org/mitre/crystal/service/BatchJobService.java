/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 *
 */
public interface BatchJobService {

	public abstract BatchJob createBatchJob(RunnableModel model,
			List<Map<String,InputNode>> vals);

	public abstract BatchJob getBatchJob(long batchJobId);

	public abstract BatchJobStatus runBatchJob(long batchJobID);

	public abstract BatchJob createAndRunBatchJob(
			RunnableModel model, List<Map<String,InputNode>> input);
	public abstract List<BatchJob> getAllBatchjobs();


	/**
	 * @param batchJob
	 */
	void deleteBatchJob(BatchJob batchJob);

}