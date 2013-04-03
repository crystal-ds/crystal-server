/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.ModelSpecificationData;

/**
 * @author tmlewis
 *
 */
public interface  ModelService {
	
	//TODO database integration
	
	public ModelSpecification saveModel(ModelSpecification modelSpec);
	public ModelSpecification getModel(long id);
	public void removeModel(ModelSpecification modelSpec);
	public List<ModelSpecification> getAllModels();
	
}
