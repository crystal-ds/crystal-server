/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 *
 */
public interface  ModelService {
	
	//TODO database integration
	
	public RunnableModel saveModel(RunnableModel modelSpec);
	public RunnableModel getModel(long id);
	public void removeModel(RunnableModel modelSpec);
	public List<RunnableModel> getAllModels();
	
}
