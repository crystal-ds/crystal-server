/**
 * Main controller 
 */
package org.mitre.crystal.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ModelService;
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
 * Controller class for the Exploritory Modeling engine
 */
@Controller
@RequestMapping (value = "/eme")
public class ExploratoryModelEngine {

	final Logger log = LoggerFactory.getLogger(ExploratoryModelEngine.class);

	@Autowired 
	private ModelService service;

	@Autowired
	private BatchJobService batchJobService;

	/**
	 * Requests all availbe EME models be returned
	 * @param m
	 * @return A string that directs to mapOfModelsView
	 */
	@RequestMapping(value = "/models", method=RequestMethod.GET, produces="application/json")
	public String getAllModels(Model m){
		log.info("Request for all Models");
		final List<RunnableModel> allModels = service.getAllModels();
		final Map<Long, RunnableModel> nameToModel = new HashMap<Long, RunnableModel>();

		for (final RunnableModel model : allModels) {
			nameToModel.put(model.getId(), model);		
		}
		m.addAttribute("mapOfModels", nameToModel);
		return "mapOfModelsView";		
	}

	/**
	 * Request of a speicfic model
	 * @param id The id of the model you want
	 * @param m 
	 * @return directs to the modelView
	 */
	@RequestMapping(value = "/models/{id}", method=RequestMethod.GET, produces="application/json")
	public String getModel(@PathVariable("id") long id, Model m){
		log.debug("Request for model " + id);

		final RunnableModel model = service.getModel(id);
		m.addAttribute("model", model);
		return "modelView";

	}

	/**
	 * Gets specific inputs for a model
	 * @param id
	 * @param m
	 * @return directs to model input view
	 */
	@RequestMapping(value = "/models/{id}/inputs", method=RequestMethod.GET, produces="application/json")
	public String getModelInputs(@PathVariable("id") long id,  Model m){
		log.info("Request for " + id + " inputs");
		final RunnableModel model = service.getModel(id);
		m.addAttribute("model", model);
		return "modelInputView";
	}


	/**
	 * Creats and runs a batch job
	 * @param id the model you want to run
	 * @param vals The list of inputs you want to run the model with
	 * @param m
	 * @return directs to batcjobIDview
	 */
	@RequestMapping(value = "/models/{id}/run", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String startRun(@PathVariable("id") long id, @RequestBody String vals, Model m){
		log.info("Running model " + id + " using inputs: " +vals );
		final RunnableModel model = service.getModel(id);
	
		List<Map<String,String>> vals2 = new ArrayList<Map<String,String>>();
		final ObjectMapper mapper = new ObjectMapper();
		try {
			vals2 = mapper.readValue(vals, vals2.getClass());
		} catch (final JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final BatchJob job= batchJobService.createAndRunBatchJob(model, vals2);

		m.addAttribute("batchJob", job);

		return "batchJobIdView";
	}

	/**
	 * Check on the status of a batchJob
	 * @param id The id of the batch job
	 * @param m 
	 * @return
	 */
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.HEAD )
	public String getStatus(@PathVariable("id") long id, Model m){
		log.debug("client is checking on result status " + id);
		//TODOcreate httpcodeview bean

		final BatchJob job = batchJobService.getBatchJob(id);

		m.addAttribute("Status", job.getStatus());

		return "httpCodeView";
	}
	
	/**
	 * Get the results of running a batchjob
	 * @param id the id of the batchjob you are looking up
	 * @return
	 */
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody BatchJob getModelResults(@PathVariable("id") long id){
		log.debug("client is requesting result set for " + id);		
		final BatchJob job = batchJobService.getBatchJob(id);
		return job;
	}
}
