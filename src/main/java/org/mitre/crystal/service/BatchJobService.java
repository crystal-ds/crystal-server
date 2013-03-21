/**
 * 
 */
package org.mitre.crystal.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobStatus;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmlewis
 *
 */
public class BatchJobService {
	
	//TODO Database integration
	
	final Logger log = LoggerFactory.getLogger(BatchJobService.class);
	public long  createBatchJob(ModelSpecification model, ModelRunInputValues vals){
		log.debug("Creating Batch Job");
		BatchJob bj = new BatchJob();
		bj.setModelId(model.getId());
		bj.setRuns(createInputsForBatchJob(vals));
		bj.setStatus(BatchJobStatus.NOT_STARTED);
		//TODO setID with automagic number from db
		bj.setId(null);
		log.info("batch Job created with ID" + bj.getId());
		return bj.getId();
	
	}
	public BatchJob getBatchJob(long batchJobId){
		BatchJob bj = new BatchJob();
		bj.setId(batchJobId);
		//TODO JPA magic!
		return bj;
	}
	public BatchJobStatus runBatchJob(long batchJobID){
		log.info("Checking status for batch job" + batchJobID);
		BatchJob bj = getBatchJob(batchJobID);
		List<ModelRunInstance> runs = bj.getRuns();
		for (ModelRunInstance run : runs) {
			//TODO figure out how to calculate status
		}

		return BatchJobStatus.UNKNOWN;
	}
		
	public BatchJobStatus createAndRunBatchJob(ModelSpecification model, ModelRunInputValues input){
		log.info("create And run BatchJob");
		return runBatchJob(createBatchJob(model, input));
		
	
	}
	public void deleteBatchJob(long batchJobId){
		log.info("removing batch job " + batchJobId); 
		
	}

	private List<ModelRunInstance> createInputsForBatchJob(ModelRunInputValues input){
		log.debug("Creating inputs for batch Job");
		//TODO do error checking on inputs
		//generates list of Model  for exploritory mdoeling run
		
		//TODO make this real
		return createDummyList();
		//log.debug("Finished creating inputs for batch job");
		//return null;
	}
	/**
	 * @return
	 */
	private List<ModelRunInstance> createDummyList() {
		// TODO Auto-generated method stub
	
		
		ArrayList<ModelRunInstance> l = new ArrayList<ModelRunInstance>();
		Random rand = new Random();
		long batchID = rand.nextLong();
		
		
		for (int i = 0; i < 100; i++) {
			ModelRunInstance mrl = new ModelRunInstance();
			mrl.setBatchId(batchID);
			mrl.setModelid((long) 343434343);
			mrl.setRunId(rand.nextLong());
			Map<String, JsonNode> map = new HashMap();
			
			
			ObjectMapper mapper = new ObjectMapper();
			String inputValues = "";
			ObjectNode on = mapper.createObjectNode();
			on.put("value", i);
			map.put("Test" + i, on.get("value"));
			ModelRunInputValues mriv = new ModelRunInputValues();
			mriv.setInputs(map);
			mrl.setInputValues(mriv);
		
			
		}
		
		return l;
	}
	
	

	
}
