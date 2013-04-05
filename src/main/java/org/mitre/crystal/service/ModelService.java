/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.ModelSpecificationData;

/**
 * @author tmlewis
 *
 */
public interface  ModelService {
	
	//TODO database integration
	
	public ModelSpecificationData saveModel(ModelSpecificationData modelSpec);
	public ModelSpecificationData getModel(long id);
	public void removeModel(ModelSpecificationData modelSpec);
	public List<ModelSpecificationData> getAllModels();
	
}
