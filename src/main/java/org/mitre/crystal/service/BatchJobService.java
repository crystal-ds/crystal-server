
package org.mitre.crystal.service;

import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 * Does CRUD operations on a BatchJob
 */
public interface BatchJobService {

	/**
	 * Creates Batch Jobs
	 * @param model The model that the batch job will be using
	 * @param vals The key value pairs that will be input into the model
	 * @return A newly created batch job
	 */
	public abstract BatchJob createBatchJob(RunnableModel model,
			List<Map<String,String>> vals);

	/**
	 * Reterives a batch job from the repository
	 * @param batchJobId The batch Job ID you are looking for
	 * @return The batch Job that was located
	 */
	public abstract BatchJob getBatchJob(long batchJobId);

	/**
	 * Runs a batch job through the model
	 * @param batchJobID The id of the batchjob you want to run
	 * @return The batchjob Status
	 */
	public abstract BatchJobStatus runBatchJob(long batchJobID);

	/**
	 * Helper function that creates and runs a batch job
	 * @param model The model that the batchjob will run through
	 * @param input The list of inputs you want to run
	 * @return
	 */
	public abstract BatchJob createAndRunBatchJob(
			RunnableModel model, List<Map<String,String>> input);
	/**
	 * 
	 * @return All available batch Jobs
	 */
	public abstract List<BatchJob> getAllBatchjobs();


	/**
	 * @param batchJob The batchjob to remove
	 */
	void deleteBatchJob(BatchJob batchJob);

}
