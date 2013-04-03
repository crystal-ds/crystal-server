/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.ModelSpecificationData;

/**
 * @author tmlewis
 *
 */
public interface ModelRepository {

	ModelSpecification saveModel(ModelSpecification model);

	/**
	 * @param id
	 * @return
	 */
	ModelSpecification getModel(long id);

	/**
	 * @param modelSpec
	 */
	void removeModel(ModelSpecification modelSpec);

	/**
	 * @return
	 */
	List<ModelSpecification> getAllModels();

}
