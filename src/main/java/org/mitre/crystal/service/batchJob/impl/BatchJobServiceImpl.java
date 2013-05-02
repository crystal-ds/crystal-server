/**
 * 
 */
package org.mitre.crystal.service.batchJob.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.InputNode;
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
	
	//TODO Database integration
	@Autowired 
	private ModelService modelService;
	@Autowired
	private BatchJobRepository bjr;
	@Autowired
	private InputDiversifier idv;
	@Autowired
	private ModelService service;
	final Logger log = LoggerFactory.getLogger(BatchJobServiceImpl.class);
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#createBatchJob(org.mitre.crystal.model.ModelSpecification, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJob createBatchJob(RunnableModel model, List<Map<String,InputNode>> vals){
		log.debug("Creating Batch Job");
		BatchJob bj = new BatchJob();
		bj.setModelID(model.getId());
		
		//List <Map<String,String>> variations = idv.diversify(model, vals);
		
		List <ModelRunInstance> instances = new ArrayList<ModelRunInstance>();
		//TEST CODE
		//List <ModelRunInputValues> variations = new ArrayList<ModelRunInputValues>();
		//variations.add(vals);
		
		Map<String,String> inputs = new HashMap<String, String>();
		List<Map<String,InputNode>> varaitations = vals;
		for (Map<String, InputNode> map : varaitations) {
			for(Entry<String, InputNode> entry : map.entrySet()){
				inputs.put(entry.getKey(), entry.getValue().getProperty("value"));
		}

			ModelRunInstance mri = new ModelRunInstance();
			mri.setInputValues(inputs);
			instances.add(mri);
		}

		//END test code
//		for (Map<String,String> inputVariation : variations) {
//			ModelRunInstance mri = new ModelRunInstance();
//			//mri.setModel(model);
//			mri.setInputValues(inputVariation);
//			instances.add(mri);
//		}
		
		bj.setInstances(instances);
		
		//bj.setRuns(createInputsForBatchJob(vals));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		BatchJob saved = bjr.save(bj);
		log.info("batch Job created with ID " + saved.getId());
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
		RunnableModel m = service.getModel(bj.getModelID());
		bj.setStatus(BatchJobStatus.RUNNING);
		for (ModelRunInstance run : runs) {
			m.runModel(run);
		}
		bj.setStatus(BatchJobStatus.COMPLETED);
		bjr.save(bj);
		return bj.getStatus();
	}
		
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.BatchJobInterface#createAndRunBatchJob(org.mitre.crystal.model.ModelSpecification, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public BatchJob createAndRunBatchJob(RunnableModel model, List<Map<String,InputNode>> input){
		log.info("create And run BatchJob");
		BatchJob batchjob = createBatchJob(model, input);
		runBatchJob(batchjob.getId());
		return batchjob;	
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
