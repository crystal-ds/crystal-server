/**
 * 
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
	public String getAllModels(Model m){
		log.info("Request for all Models");
		
		List<RunnableModel> allModels = service.getAllModels();
		Map<Long, RunnableModel> nameToModel = new HashMap<Long, RunnableModel>();
		
		for (RunnableModel model : allModels) {
			nameToModel.put(model.getId(), model);		
		}

		m.addAttribute("mapOfModels", nameToModel);
		return "mapOfModelsView";		
	}
	
	@RequestMapping(value = "/models/{id}", method=RequestMethod.GET, produces="application/json")
	public String getModel(@PathVariable("id") long id, Model m){
		log.debug("Request for model " + id);
		
		RunnableModel model = service.getModel(id);
		m.addAttribute("model", model);
		return "modelView";

	}

	@RequestMapping(value = "/models/{id}/inputs", method=RequestMethod.GET, produces="application/json")
	public String getModelInputs(@PathVariable("id") long id,  Model m){
		log.info("Request for " + id + " inputs");
		
		RunnableModel model = service.getModel(id);
		m.addAttribute("model", model);
		
		//return model.getInputs();
		return "modelInputView";
	}
	
	
	@RequestMapping(value = "/models/{id}/run", method=RequestMethod.POST, produces="application/json", consumes="application/json")
	public String startRun(@PathVariable("id") long id, @RequestBody String vals, Model m){
		log.info("Running model " + id + " using inputs: " +vals );
		RunnableModel model = service.getModel(id);
		
		//BatchJob job = batchJobService.createAndRunBatchJob(model, vals);		
		List<Map<String,String>> vals2 = new ArrayList<Map<String,String>>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			vals2 = mapper.readValue(vals, vals2.getClass());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		BatchJob job= batchJobService.createAndRunBatchJob(model, vals2);
		
		m.addAttribute("batchJob", job);
		
		return "batchJobIdView";
	}
	//TEST CODE
//	@RequestMapping(value = "/models/{id}/testrun", method=RequestMethod.GET, produces="application/json")
//	public String startTestRun(@PathVariable("id") long id, Model m){
//		log.info("test Running model " + id);
//		RunnableModel model = service.getModel(id);
//
//		List<Map<String,InputNode>> l = new ArrayList<Map<String, InputNode>>();
//		Map<String,InputNode> m1 = new HashMap<String, InputNode>();
//		Map<String,String> prop = new HashMap<String, String>();
//		InputNode i = new InputNode();
//		i.setName("Input 1");
//		i.setType(InputType.CHECKBOX);
//	
//		prop.put("checked", "True");
//		prop.put("value", "true");
//		i.setProperties(prop);
//		m1.put("Input 1", i);
//		l.add(m1);
//		
//		BatchJob job = batchJobService.createBatchJob(model, l);		
//		
//		m.addAttribute("batchJob", job);
//		
//		return "batchJobIdView";
//	}
//	@RequestMapping(value = "/models/{id}/testrun2", method=RequestMethod.GET, produces="application/json")
//	public String startTestRun2(@PathVariable("id") long id, Model m){
//		log.info("test Running model " + id);
//		RunnableModel model = service.getModel(id);
//		
//		
//		List<Map<String,InputNode>> l = new ArrayList<Map<String, InputNode>>();
//		Map<String,InputNode> m1 = new HashMap<String, InputNode>();
//		Map<String,String> prop = new HashMap<String, String>();
//		InputNode i = new InputNode();
//		i.setName("Input 1");
//		i.setType(InputType.CHECKBOX);
//	
//		prop.put("checked", "True");
//		prop.put("value", "true");
//		i.setProperties(prop);
//		m1.put("Input 1", i);
//		l.add(m1);
//		
//		
//		Map<String, String> inputs = new HashMap<String, String>();
//		inputs.put("thing1", "thing2");
//		BatchJob job = batchJobService.createAndRunBatchJob(model, l);		
//		
//		m.addAttribute("batchJob", job);
//		
//		return "batchJobIdView";
//	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.HEAD )
	public String getStatus(@PathVariable("id") long id, Model m){
		log.debug("client is checking on result status " + id);
		//TODOcreate httpcodeview bean
		
		BatchJob job = batchJobService.getBatchJob(id);
			
		m.addAttribute("Status", job.getStatus());
		
		return "httpCodeView";
	}
	@RequestMapping(value = "/resultsets/{id}", method=RequestMethod.GET, produces="application/json" )
	public @ResponseBody BatchJob getModelResults(@PathVariable("id") long id){
		log.debug("client is requesting result set for " + id);		
		BatchJob job = batchJobService.getBatchJob(id);
		
		
		return job;
		
		
	}
	
}
