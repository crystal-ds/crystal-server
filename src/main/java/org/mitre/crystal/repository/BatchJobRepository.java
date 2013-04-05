/**
 * 
 */
package org.mitre.crystal.repository;

import org.mitre.crystal.model.BatchJob;

/**
 * @author tmlewis
 *
 */
public interface BatchJobRepository {

	/**
	 * @param bj
	 * @return
	 */
	BatchJob save(BatchJob bj);

	/**
	 * @param batchJobId
	 * @return
	 */
	BatchJob getBatchJob(long batchJobId);

	/**
	 * @param batchJob
	 */
	void deleteBatchJob(BatchJob batchJob);

}