/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.ModelSpecificationData;

/**
 * @author tmlewis
 *
 */
public interface ModelRepository {

	ModelSpecificationData saveModel(ModelSpecificationData model);

	/**
	 * @param id
	 * @return
	 */
	ModelSpecificationData getModel(long id);

	/**
	 * @param modelSpec
	 */
	void removeModel(ModelSpecificationData modelSpec);

	/**
	 * @return
	 */
	List<ModelSpecificationData> getAllModels();

}
