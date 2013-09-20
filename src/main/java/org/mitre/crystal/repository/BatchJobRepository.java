/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.BatchJob;

/**
 * @author tmlewis
 * Interface for the BatchJobRepository. These are the methods that any BJ repo will need to support
 * 
 */
public interface BatchJobRepository {

	/**
	 * @param bj Batchjob to be saved
	 * @return The saved batched job complete with ID
	 */
	BatchJob save(BatchJob bj);

	/**
	 * @param batchJobId BatchJob ID that you want to reterive
	 * @return The Batchjob you are looking for or null if not found
	 */
	BatchJob getBatchJob(long batchJobId);

	/**
	 * @param batchJob Batchjob to remove. 
	 */
	void deleteBatchJob(BatchJob batchJob);
	
	/**
	 * 
	 * @return All batchjobs availabe regardless of status
	 */
	List<BatchJob> getAllBatchJobs();

}
