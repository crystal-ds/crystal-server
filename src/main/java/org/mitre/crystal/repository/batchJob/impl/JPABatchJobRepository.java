/**
 * 
 */
package org.mitre.crystal.repository.batchJob.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.repository.BatchJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tmlewis
 *
 */
@Repository("jpaBatchJobRepository")
@NamedQueries({
	@NamedQuery(name="BatchJob.findAll", query="SELECT * FROM score_batch_job")
})
public class JPABatchJobRepository implements BatchJobRepository {

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);
	
	@PersistenceContext
	private EntityManager manager;
	
	public JPABatchJobRepository(){
		

		
	}
	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.BatchJobRepository#save(org.mitre.crystal.model.BatchJob)
	 */
	@Override
	@Transactional
	public BatchJob save(BatchJob batchJob) {
		if(batchJob.getId() == null){
			//new
			manager.persist(batchJob);
			manager.flush();
			return batchJob;
		}
		else{
			BatchJob batch = manager.merge(batchJob);
			manager.flush();
			return batch;
		}
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.BatchJobRepository#getBatchJob(long)
	 */
	@Override
	@Transactional
	public BatchJob getBatchJob(long batchJobId) {
		return manager.find(BatchJob.class, batchJobId);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.BatchJobRepository#deleteBatchJob(org.mitre.crystal.model.BatchJob)
	 */
	@Override
	@Transactional
	public void deleteBatchJob(BatchJob batchJob) {
		BatchJob batch = getBatchJob(batchJob.getId());
		if(batch != null){
			manager.remove(batch);
		}
		

	}
	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.BatchJobRepository#getAllBatchJobs()
	 */
	@Override
	public List<BatchJob> getAllBatchJobs() {
	
		Query q =  manager.createNamedQuery("BatchJob.findAll");
		return q.getResultList();
	}

}
