/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.model.ScoringModelInput;

/**
 * @author tmlewis
 *
 */
public interface ScoringBatchJobService {

	/**
	 * @param id
	 * @return
	 */
	public abstract SMBatchJob getScore(long id);

	/**
	 * @param vals
	 * @param job
	 * @param model 
	 * @return
	 */
	public abstract SMBatchJob score(ScoringModelInput vals, BatchJob job, ScoringModel model);

	/**
	 * @param id
	 * @return
	 */
	public abstract SMBatchJob getSMBatchJob(long id);
	
	public abstract void deleteSMBatchJob(long id);

}
