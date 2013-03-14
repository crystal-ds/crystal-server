/**
 * 
 */
package org.mitre.crystal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.InputSpecification;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ModelService;
import org.mitre.crystal.web.view.runIDview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping (value = "/eme")
public class ExploratoryModelEngine {

	final Logger log = LoggerFactory.getLogger(ExploratoryModelEngine.class);
	
	@Autowired 
	private ModelService service;
	
	@Autowired
	private BatchJobService batchJobService;
	
	@RequestMapping(value = "/models", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<Long,ModelSpecification> getAllModels(){
		log.debug("Request for all Models");
		
		List<ModelSpecification> allModels = service.getAllModels();
		Map<Long, ModelSpecification> nameToModel = new HashMap<Long, ModelSpecification>();
		
		for (ModelSpecification model : allModels) {
			nameToModel.put(model.getId(), model);
			
		}
		return nameToModel;
		
	}
	
	@RequestMapping(value = "/models/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody ModelSpecification getModel(@PathVariable("id") long id){
		log.debug("Request for model " + id);
		
		ModelSpecification model = service.getModel(id);
		return model;

	}
	@RequestMapping(value = "/models/{id}/inputs", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String,InputSpecification> getModelInputs(@PathVariable("id")long id){
		log.debug("Request for " + id + "inputs");
		
		ModelSpecification model = service.getModel(id);
		return model.getInputs();
				
		
	}
	@RequestMapping(value = "/models/{id}/run", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody runIDview startRun(@PathVariable("id") long id, @RequestBody ModelRunInputValues vals, Model m){
		log.debug("Running model " + id);
		ModelSpecification model = service.getModel(id);
		
		long job = batchJobService.createBatchJob(model, vals);		
		
		m.addAttribute("batchjob", job);
		
		return "batchJobIdView";
	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.HEAD )
	public String getStatus(@PathVariable("id") long id, Model m){
		log.debug("client is checking on result status " + id);
		//TODOcreate httpcodeview bean
		
		BatchJob job = batchJobService.getBatchJob(id);
		
		job.getStatus(); // do something with that
		
		m.addAttribute("code", HttpStatus.CONTINUE);
		
		return "httpCodeView";
	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody BatchJob getModelResults(@PathVariable("id") long id){
		log.debug("client is requesting result set for " + id);
		//query database for run ID
		//format results
		//respond
		
		BatchJob job = batchJobService.getBatchJob(id);
		
		//TODO how to I convert from a batchjob status to a response?
		//return job;
		
		
	}
	
}
