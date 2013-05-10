/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.model.ScoringModelInput;
import org.mitre.crystal.repository.ScoringBatchJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tmlewis
 *
 */
public class ScoringBatchJobServiceImpl implements ScoringBatchJobService {

	
	@Autowired
	private ScoringBatchJobRepository sbjr;
	
	final Logger log = LoggerFactory.getLogger(ScoringModelService.class);
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringBatchJobService#getScore(long)
	 */
	@Override
	public SMBatchJob getScore(long id) {
		log.info("Getting Score for " + id);
		return sbjr.getBatchJob(id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringBatchJobService#score(org.mitre.crystal.model.ScoringModelInput, org.mitre.crystal.model.BatchJob)
	 */
	@Override
	public SMBatchJob score(ScoringModelInput vals, BatchJob job, ScoringModel model) {
		log.info("Scoring batchJob " + job.getId());
		SMBatchJob score = model.score(vals,job);
		sbjr.save(score);
		return null;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringBatchJobService#getSMBatchJob(long)
	 */
	@Override
	public SMBatchJob getSMBatchJob(long id) {
		return sbjr.getBatchJob(id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringBatchJobService#deleteSMBatchJob(long)
	 */
	@Override
	public void deleteSMBatchJob(long id) {
		log.info("Deleting Scoreing model batch job " + id);
		SMBatchJob s = sbjr.getBatchJob(id);
		sbjr.deleteBatchJob(s);
	}

}
