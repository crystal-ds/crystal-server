/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.BatchJobRunStatus;
import org.mitre.crystal.model.InputSpecification;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecification;

/**
 * @author tmlewis
 *
 */
public class ModelService {
	
	//TODO database integration
	
	public Long createModel(String name, String description, Map inputs){
		
		//stores in database for later

	
	}
	public ModelSpecification getModel(long id){
		
	}
	public void removeModel(){
		
	}
	
	public List getAllModels(){
		
	}
	
}
