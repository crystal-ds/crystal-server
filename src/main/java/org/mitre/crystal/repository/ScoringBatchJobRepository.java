/**
 * 
 */
package org.mitre.crystal.repository;

import org.mitre.crystal.model.SMBatchJob;

/**
 * @author tmlewis
 * Interface that any ScoreingBatchJobRepository will need to implement
 */
public interface ScoringBatchJobRepository {

	/**
	 * @param id ID of the batchjob you are looking for
	 * @return The Scoreing model batch job requested, otherwise null
	 */
	SMBatchJob getBatchJob(long id);

	/**
	 * @param score The SM batchJob you wish to save
	 * @return The SMBatchJob that that has been saved, complete with ID
	 */
	SMBatchJob save(SMBatchJob score);

	/**
	 * @param id Removes this batchjob
	 */
	void deleteBatchJob(SMBatchJob job);

}
