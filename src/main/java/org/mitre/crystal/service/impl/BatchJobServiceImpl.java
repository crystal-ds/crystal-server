/**
 * 
 */
package org.mitre.crystal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.ModelSpecificationData;
import org.mitre.crystal.repository.BatchJobRepository;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ModelService;
import org.mitre.crystal.service.diversifier.InputDiversifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service
public class BatchJobServiceImpl implements BatchJobService {
	
	//TODO Database integration
	@Autowired 
	private ModelService modelService;
	@Autowired
	private BatchJobRepository bjr;
	@Autowired
	private InputDiversifier idv;
	
	final Logger log = LoggerFactory.getLogger(BatchJobServiceImpl.class);
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#createBatchJob(org.mitre.crystal.model.ModelSpecificationData, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJob createBatchJob(ModelSpecification model, ModelRunInputValues vals){
		log.debug("Creating Batch Job");
		BatchJob bj = new BatchJob();
		bj.setModel(model);
		
		List <ModelRunInputValues> variations = idv.diversify(model, vals);
		List <ModelRunInstance> instances = new ArrayList<ModelRunInstance>();
		for (ModelRunInputValues inputVariation : variations) {
			ModelRunInstance mri = new ModelRunInstance();
			mri.setModel(model);
			mri.setInputValues(inputVariation);
			instances.add(mri);
		}
		
		bj.setInstances(instances);
		
		//bj.setRuns(createInputsForBatchJob(vals));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		BatchJob saved = bjr.save(bj);
		log.info("batch Job created with ID" + saved.getId());
		return saved;
	
	}
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#getBatchJob(long)
	 */
	@Override
	public BatchJob getBatchJob(long batchJobId){
		return bjr.getBatchJob(batchJobId);
		
	}
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#runBatchJob(long)
	 */
	@Override
	public BatchJobStatus runBatchJob(long batchJobID){
		log.info("Checking status for batch job" + batchJobID);
		BatchJob bj = getBatchJob(batchJobID);
		List<ModelRunInstance> runs = bj.getInstances();
		for (ModelRunInstance run : runs) {
			//TODO figure out how to calculate status
			ModelSpecification m = bj.getModel();
			m.runModel(run);
		
		}
		//TODO figure out status
		return BatchJobStatus.UNKNOWN;
	}
		
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#createAndRunBatchJob(org.mitre.crystal.model.ModelSpecificationData, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJobStatus createAndRunBatchJob(ModelSpecification model, ModelRunInputValues input){
		log.info("create And run BatchJob");
		BatchJob batchjob = createBatchJob(model, input);
		return runBatchJob(batchjob.getId());
		
	
	}
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#deleteBatchJob(long)
	 */
	@Override
	public void deleteBatchJob(BatchJob batchJob){
		log.info("removing batch job " + batchJob.getId());
		
		bjr.deleteBatchJob(batchJob);
	}

	
//	private List<ModelRunInstance> createInputsForBatchJob(ModelRunInputValues input){
//		log.debug("Creating inputs for batch Job");
//		//TODO do error checking on inputs
//		//generates list of Model  for exploritory mdoeling run
//		
//		//TODO make this real
//		return createDummyList();
//		//log.debug("Finished creating inputs for batch job");
//		//return null;
//	}
//	/**
//	 * @return
//	 */
//	private List<ModelRunInstance> createDummyList() {
//		// TODO Auto-generated method stub
//	
//		
//		ArrayList<ModelRunInstance> l = new ArrayList<ModelRunInstance>();
//		Random rand = new Random();
//		long batchID = rand.nextLong();
//		
//		
//		for (int i = 0; i < 100; i++) {
//			ModelRunInstance mrl = new ModelRunInstance();
//			mrl.setBatchId(batchID);
//			mrl.setModelid((long) 343434343);
//			mrl.setRunId(rand.nextLong());
//			Map<String, JsonNode> map = new HashMap();
//			
//			
//			ObjectMapper mapper = new ObjectMapper();
//			String inputValues = "";
//			ObjectNode on = mapper.createObjectNode();
//			on.put("value", i);
//			map.put("Test" + i, on.get("value"));
//			ModelRunInputValues mriv = new ModelRunInputValues();
//			mriv.setInputs(map);
//			mrl.setInputValues(mriv);
//		
//			
//		}
//		
//		return l;
//	}
//	
//	
//
//	
}
