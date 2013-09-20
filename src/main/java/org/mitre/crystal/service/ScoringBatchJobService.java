/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.model.ScoringModelInput;

/**
 * @author tmlewis
 * Manages Scoring Batch jobs
 */
public interface ScoringBatchJobService {

	/**
	 * @param id Id of the scoreing batch job you are looking for
	 * @return The scoreing batch job
	 */
	public abstract SMBatchJob getScore(long id);

	/**
	 * @param vals The inputs or rubric you want to score against
	 * @param job The batch job you wish to score
	 * @param model  The socring model you wish to use
	 * @return The Scoreing Batch job created with the above information
	 */
	public abstract SMBatchJob score(ScoringModelInput vals, BatchJob job, ScoringModel model);

	/**
	 * @param id The scoreing batch job you wish to return
	 * @return The batch job requested
	 */
	public abstract SMBatchJob getSMBatchJob(long id);
	/**
	 * 
	 * @param id The SM batchjob you want to remove
	 */
	public abstract void deleteSMBatchJob(long id);

}
