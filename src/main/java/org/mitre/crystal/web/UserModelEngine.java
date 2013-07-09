/**
 * 
 */
package org.mitre.crystal.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.service.BatchJobService;
import org.mitre.crystal.service.ScoringBatchJobService;
import org.mitre.crystal.service.ScoringModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author tmlewis
 *
 */
@Controller
@RequestMapping (value = "/user")
public class UserModelEngine {
	
	final Logger log = LoggerFactory.getLogger(ExploratoryModelEngine.class);
	
	@Autowired
	private BatchJobService batchJobService;
	
	@Autowired
	private ScoringBatchJobService scoringService;
	
	@RequestMapping(value = "/models", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<BatchJob> getUserModels(Model m){
		return batchJobService.getAllBatchjobs();
	}
	
	@RequestMapping(value = "/models/{id}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Map<BatchJob,SMBatchJob> getModelData(@PathVariable("id") long id,  Model m){
		HashMap<BatchJob, SMBatchJob> data = new HashMap<BatchJob, SMBatchJob>();
		BatchJob bj = batchJobService.getBatchJob(id);
		SMBatchJob smbj = scoringService.getScore(id);
		data.put(bj, smbj);
		return data;
	}
	
	
}
