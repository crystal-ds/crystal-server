/**
 * 
 */
package org.mitre.crystal.service.batchJob.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.repository.BatchJobRepository;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 * Implementation of a batchjob service
 */
@Service("batchJobService")
public class BatchJobServiceImpl implements BatchJobService {


	@Autowired
	private ModelService modelService;
	@Autowired
	private BatchJobRepository bjr;
	@Autowired
	private ModelService service;
	final Logger log = LoggerFactory.getLogger(BatchJobServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mitre.crystal.service.BatchJobInterface#createBatchJob(org.mitre.
	 * crystal.model.ModelSpecification,
	 * org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJob createBatchJob(RunnableModel model,
			List<Map<String, String>> vals) {
		log.debug("Creating Batch Job");
		final BatchJob bj = new BatchJob();
		bj.setModelID(model.getId());
		final List<ModelRunInstance> instances = new ArrayList<ModelRunInstance>();


		int count = 0;
		Map<String, String> inputs = new HashMap<String, String>();
		//Puts the front end inputs into a model run instance
		for (final Map<String, String> map : vals) {
			final Set<Entry<String, String>> s = map.entrySet();
			for (final Entry<String, String> entry : s) {
				inputs.put(entry.getKey().toString(), entry.getValue().toString());
			}
			final ModelRunInstance mri = new ModelRunInstance();
			mri.setInputValues(inputs);
			instances.add(mri);
			inputs =  new HashMap<String, String>();
			count ++;
		}

		bj.setInstances(instances);


		bj.setStatus(BatchJobStatus.NOT_STARTED);
		final BatchJob saved = bjr.save(bj);
		log.info("batch Job created with ID " + saved.getId() + " with " + count + "runs");
		return saved;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#getBatchJob(long)
	 */
	@Override
	public BatchJob getBatchJob(long batchJobId) {
		return bjr.getBatchJob(batchJobId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#runBatchJob(long)
	 */
	@Override
	public BatchJobStatus runBatchJob(long batchJobID) {
		log.info("Checking status for batch job" + batchJobID);
		final BatchJob bj = getBatchJob(batchJobID);
		final List<ModelRunInstance> runs = bj.getInstances();
		final RunnableModel m = service.getModel(bj.getModelID());
		bj.setStatus(BatchJobStatus.RUNNING);
		for (final ModelRunInstance run : runs) {
			m.runModel(run);
		}
		bj.setStatus(BatchJobStatus.COMPLETED);
		bjr.save(bj);
		return bj.getStatus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mitre.crystal.service.BatchJobInterface#createAndRunBatchJob(org.
	 * mitre.crystal.model.ModelSpecification,
	 * org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJob createAndRunBatchJob(RunnableModel model,
			List<Map<String, String >> input) {
		log.info("create And run BatchJob");
		final BatchJob batchjob = createBatchJob(model, input);
		runBatchJob(batchjob.getId());
		return batchjob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#deleteBatchJob(long)
	 */
	@Override
	public void deleteBatchJob(BatchJob batchJob) {
		log.info("removing batch job " + batchJob.getId());

		bjr.deleteBatchJob(batchJob);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobService#getAllBatchjobs()
	 */
	@Override
	public List<BatchJob> getAllBatchjobs() {
		log.info("get All Batchjobs");
		return bjr.getAllBatchJobs();
	}
}
