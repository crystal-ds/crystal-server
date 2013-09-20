/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 * This is they way we manage the interface classes to models. 
 */
public interface ModelRepository {

	RunnableModel saveModel(RunnableModel model);

	/**
	 * @param id The id of the model you are requesting
	 * @return the RunnableModel if found. Null if not.
	 */
	RunnableModel getModel(long id);

	/**
	 * @param model The model you want to remove. 
	 */
	void removeModel(RunnableModel model);

	/**
	 * @return A list of all RunnableModels that are availble. 
	 */
	List<RunnableModel> getAllModels();

}
