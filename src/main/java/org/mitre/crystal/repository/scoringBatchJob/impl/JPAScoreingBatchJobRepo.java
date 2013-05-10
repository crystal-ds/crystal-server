/**
 * 
 */
package org.mitre.crystal.repository.scoringBatchJob.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.repository.ScoringBatchJobRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tmlewis
 *
 */
@Repository("JPAScoreingBatchJobRepository")
public class JPAScoreingBatchJobRepo implements ScoringBatchJobRepository {

	
	final org.slf4j.Logger log = LoggerFactory.getLogger(JPAScoreingBatchJobRepo.class);
	
	@PersistenceContext
	private EntityManager manager;
	
	
	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringBatchJobRepository#getBatchJob(long)
	 */
	@Override
	@Transactional
	public SMBatchJob getBatchJob(long id) {
		return manager.find(SMBatchJob.class, id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringBatchJobRepository#save(org.mitre.crystal.model.SMBatchJob)
	 */
	@Override
	@Transactional
	public SMBatchJob save(SMBatchJob score) {
		if(score.getId() == null){
			manager.persist(score);
			manager.flush();
			return score;
		}else{
			SMBatchJob s = manager.merge(score);
			manager.flush();
			return s;
		}

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringBatchJobRepository#deleteBatchJob(long)
	 */
	@Override
	@Transactional
	public void deleteBatchJob(SMBatchJob job) {
		SMBatchJob s = getBatchJob(job.getId());
		if (s != null){
			manager.remove(job);
		}

	}

}
