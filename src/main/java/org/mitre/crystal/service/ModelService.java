/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 * CRUD operations for Runnable Models
 */
public interface  ModelService {

	/**
	 * 
	 * @param modelSpec Saves a model 
	 * @return The saved model complete with ID
	 */
	public RunnableModel saveModel(RunnableModel modelSpec);
	/**
	 * Retrieves a model from the repo
	 * @param id The ID of the model
	 * @return The model
	 */
	public RunnableModel getModel(long id);
	/**
	 * Removes a model from the repo
	 * @param modelSpec The model to remove
	 */
	public void removeModel(RunnableModel modelSpec);
	/**
	 * A list of all models
	 * @return A list of all available models
	 */
	public List<RunnableModel> getAllModels();

}
