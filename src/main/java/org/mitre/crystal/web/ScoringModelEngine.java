/**
 * 
 */
package org.mitre.crystal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.model.ScoringModelInput;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ScoringBatchJobService;
import org.mitre.crystal.service.ScoringModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tmlewis
 *
 */
@Controller
@RequestMapping (value = "/sme")
public class ScoringModelEngine {

	final Logger log = LoggerFactory.getLogger(ScoringModelEngine.class);
	
	@Autowired 
	private ScoringModelService service;
	
	@Autowired
	private BatchJobService batchJobService;
	
	@Autowired
	private ScoringBatchJobService scoreService;
	
	
	@RequestMapping(value = "/models", method=RequestMethod.GET, produces="application/json")
	public String getAllModels(Model m){
		log.info("Request for all Scoring Models");
		
		List<ScoringModel> allModels = service.getAllModels();
		Map<Long, ScoringModel> nameToModel = new HashMap<Long, ScoringModel>();
		
		for (ScoringModel model : allModels) {
			nameToModel.put(model.getId(), model);		
		}

		m.addAttribute("mapOfModels", nameToModel);
		return "mapOfModelsView";		
	}
	@RequestMapping(value = "/models/{id}", method=RequestMethod.GET, produces="application/json")
	public String getModel(@PathVariable("id") long id, Model m){
		log.debug("Request for  scoreing model " + id);
		
		ScoringModel model = service.getModel(id);
		m.addAttribute("model", model);
		return "scoringModelView";

	}
	@RequestMapping(value = "/models/{id}/inputs", method=RequestMethod.GET, produces="application/json")
	public String getModelInputs(@PathVariable("id") long id,  Model m){
		log.info("Request for " + id + " inputs");
		
		ScoringModel model = service.getModel(id);
		m.addAttribute("model", model);
		
		//return model.getInputs();
		return "modelInputView";
	}
	
	@RequestMapping(value = "/models/{id}/run", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String startRun(@PathVariable("id") long id, @RequestBody ScoringModelInput vals, Model m){
		log.info("Running model " + id + " using inputs: " +vals );
		ScoringModel model = service.getModel(id);
		
		BatchJob job = batchJobService.getBatchJob(vals.getBatchJobId());	
		SMBatchJob scores = scoreService.score(vals,job,model);
		
		m.addAttribute("scoreBatchJob", scores);
		
		return "scoreBatchJobView";
	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.HEAD )
	public String getStatus(@PathVariable("id") long id, Model m){
		log.debug("client is checking on result status " + id);
		
		SMBatchJob job = scoreService.getSMBatchJob(id);
			
		m.addAttribute("Status", job.getStatus());
		
		return "httpCodeView";
	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody SMBatchJob getModelResults(@PathVariable("id") long id){
		log.debug("client is requesting result set for " + id);		
		SMBatchJob job = scoreService.getScore(id);
		
		
		return job;
		
		
	}
	
	@RequestMapping(value = "/test1", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody ScoringModelInput testCode(){
		log.info("testing");
		ScoringModel model = service.getModel(1);
		ScoringModelInput smi = new ScoringModelInput();
		smi.setBatchJobId((long) 1);
		smi.setInputs(model.getInputs());
		return smi;
	}
}
