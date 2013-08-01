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

import org.codehaus.jackson.map.ObjectMapper;
import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.repository.BatchJobRepository;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.InputDiversifier;
import org.mitre.crystal.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 * 
 */
@Service("batchJobService")
public class BatchJobServiceImpl implements BatchJobService {


	@Autowired
	private ModelService modelService;
	@Autowired
	private BatchJobRepository bjr;
	@Autowired
	private InputDiversifier idv;
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
	public BatchJob createBatchJob(RunnableModel model,
			List<Map<String, String>> vals) {
		log.debug("Creating Batch Job");
		BatchJob bj = new BatchJob();
		bj.setModelID(model.getId());
		List<ModelRunInstance> instances = new ArrayList<ModelRunInstance>();


		Map<String, String> inputs = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		int count = 0;

		for (Map<String, String> map : vals) {
			Set<Entry<String, String>> s = map.entrySet();
			for (Entry<String, String> entry : s) {
				inputs.put(entry.getKey(), entry.getValue());
			}
			count ++;
		}

		ModelRunInstance mri = new ModelRunInstance();
		mri.setInputValues(inputs);
		instances.add(mri);
		bj.setInstances(instances);

		// bj.setRuns(createInputsForBatchJob(vals));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		BatchJob saved = bjr.save(bj);
		log.info("batch Job created with ID " + saved.getId() + " with " + count + "runs");
		return saved;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#getBatchJob(long)
	 */
	public BatchJob getBatchJob(long batchJobId) {
		return bjr.getBatchJob(batchJobId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#runBatchJob(long)
	 */
	public BatchJobStatus runBatchJob(long batchJobID) {
		log.info("Checking status for batch job" + batchJobID);
		BatchJob bj = getBatchJob(batchJobID);
		List<ModelRunInstance> runs = bj.getInstances();
		RunnableModel m = service.getModel(bj.getModelID());
		bj.setStatus(BatchJobStatus.RUNNING);
		for (ModelRunInstance run : runs) {
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
	public BatchJob createAndRunBatchJob(RunnableModel model,
			List<Map<String, String >> input) {
		log.info("create And run BatchJob");
		BatchJob batchjob = createBatchJob(model, input);
		runBatchJob(batchjob.getId());
		return batchjob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mitre.crystal.service.BatchJobInterface#deleteBatchJob(long)
	 */
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
