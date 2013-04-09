/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 *
 */
public interface ModelRepository {

	RunnableModel saveModel(RunnableModel model);

	/**
	 * @param id
	 * @return
	 */
	RunnableModel getModel(long id);

	/**
	 * @param model
	 */
	void removeModel(RunnableModel model);

	/**
	 * @return
	 */
	List<RunnableModel> getAllModels();

}
