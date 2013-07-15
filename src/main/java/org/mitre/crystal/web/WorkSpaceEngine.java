/**
 * 
 */
package org.mitre.crystal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ScoreRunInstance;
import org.mitre.crystal.model.WorkSpace;
import org.mitre.crystal.service.WorkSpaceService;
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
@RequestMapping (value = "/workspace")
public class WorkSpaceEngine {

	
	final Logger log = LoggerFactory.getLogger(WorkSpaceEngine.class);
	
	@Autowired
	private WorkSpaceService wss;
	
	
	@RequestMapping(value = "/createworkspace/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody WorkSpace Creation(@PathVariable("id") long id, Model m){
		log.info("Controller for create workspace for " + id + " called");
		return wss.createWorkSpace(id);
		
	}
	@RequestMapping(value = "/getworkspace/{id}", method =RequestMethod.GET, produces="application/json")
	public @ResponseBody WorkSpace getWorkSpace(@PathVariable("id") long id, Model m){
		log.info("Controller for get workspace called for " + id + id);
		return wss.getWorkSpace(id);
	}
	
	@RequestMapping(value = "/updateworkspace/{id}", method =RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody WorkSpace updateWorkSpace(@PathVariable("id") long workSpaceID, @RequestBody List<Long> mask, Model m){
		log.info("Controler for updateWorkspace called for " + workSpaceID);
		return wss.updateWorkSpaceWithLong(workSpaceID, mask);
	}
	
	@RequestMapping(value = "/restoreworkspace/", method =RequestMethod.POST, produces="application/json", consumes="application/json")
	public @ResponseBody WorkSpace restoreWorkSpace(@RequestBody  WorkSpace ws, Model m){
		log.info("Controller for restoreWorkspace called for " + ws.getWorkSpaceID());
		return wss.restoreWorkSpace(ws);
	}
	
	@RequestMapping(value = "/deleteworkspace/", method =RequestMethod.POST, produces="application/json", consumes="application/json")
	public void deleteWorkSpace(@RequestBody  WorkSpace ws, Model m){
		log.info("Controler called for delete workspace " + ws.getWorkSpaceID());
		wss.deleteWorkSpace(ws);
	}
	@RequestMapping(value = "/getworkspacemap/{id}", method =RequestMethod.GET, produces="application/json")
	public String getWorkSpacemap(@PathVariable("id") long id, Model m){
		log.info("Controller for get workspace called for " + id + id);
		WorkSpace w =wss.getWorkSpace(id);
		//return w.getWorkSpaceMap();
		Map<ModelRunInstance, ScoreRunInstance> m2 = w.getWorkSpaceMap();
		m.addAttribute("map", m2);
		return "mapView";
	}
	
	
	
	//BELOW is some simple test code
	
	@RequestMapping(value = "/testupdate/", method =RequestMethod.GET, produces="application/json")
	public @ResponseBody WorkSpace testUpdate(Model m){
		log.info("testing update");
		List<Long> l = new ArrayList<Long>();
		l.add((long)1);
		l.add((long)2);
		l.add((long)3);
		return wss.updateWorkSpaceWithLong((long)1, l);
	}
	@RequestMapping(value = "/testrestore/", method =RequestMethod.GET, produces="application/json")
	public @ResponseBody WorkSpace testRestore(Model m){
		log.info("Testing restore");
		WorkSpace w = wss.getWorkSpace((long)1);
		return wss.restoreWorkSpace(w);
	}
	@RequestMapping(value = "/testdelete/", method =RequestMethod.GET, produces="application/json")
	public @ResponseBody WorkSpace testdelete(Model m){
		log.info("Testing restore");
		WorkSpace w = wss.getWorkSpace((long)1);
		wss.deleteWorkSpace(w);
		return wss.getWorkSpace((long)1);	
	}
}
