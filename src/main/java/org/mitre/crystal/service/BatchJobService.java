/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecificationData;

/**
 * @author tmlewis
 *
 */
public interface BatchJobService {

	public abstract BatchJob createBatchJob(ModelSpecificationData model,
			ModelRunInputValues vals);

	public abstract BatchJob getBatchJob(long batchJobId);

	public abstract BatchJobStatus runBatchJob(long batchJobID);

	public abstract BatchJobStatus createAndRunBatchJob(
			ModelSpecificationData model, ModelRunInputValues input);


	/**
	 * @param batchJob
	 */
	void deleteBatchJob(BatchJob batchJob);

}