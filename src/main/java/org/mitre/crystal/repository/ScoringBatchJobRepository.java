/**
 * 
 */
package org.mitre.crystal.repository;

import org.mitre.crystal.model.SMBatchJob;

/**
 * @author tmlewis
 *
 */
public interface ScoringBatchJobRepository {

	/**
	 * @param id
	 * @return
	 */
	SMBatchJob getBatchJob(long id);

	/**
	 * @param score
	 * @return 
	 */
	SMBatchJob save(SMBatchJob score);

	/**
	 * @param id
	 */
	void deleteBatchJob(SMBatchJob job);

}
