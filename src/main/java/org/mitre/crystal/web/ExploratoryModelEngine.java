/**
 * 
 */
package org.mitre.crystal.web;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.mitre.crystal.model.InputSpecification;
import org.mitre.crystal.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	
	@RequestMapping(value = "/models", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Map<String,Model> getAllModels(){
		
	}
	@RequestMapping(value = "/models/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Model getModel(@PathVariable("id") Long id){
		
	}
	@RequestMapping(value = "/models/{id}/inputs", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody InputSpecification getModelInputs(@PathVariable("id")Long id){
		
	}
	//Should this be getRunID?
	@RequestMapping(value = "/models/{id}/run", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Long startRun(@PathVariable("id") Long id){
		
	}
	//What does this produce?
	@RequestMapping(value = "/resultsets/{id}", methond=RequestMethod.HEAD )
	public HttpServletResponse getSTatus(@PathVariable("id") Long id){
		
	}
	@RequestMapping(value = "/resultsets/{id}", methond=RequestMethod.GET, produces="application/json" )
	public @ResponseBody RunGroup getModelResults(@PathVariable("id") Long id){
		
	}
	
}
