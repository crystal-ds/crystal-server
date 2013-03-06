/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.ModelSpecification;

/**
 * @author tmlewis
 *
 */
public interface ExploratoryModelEngineServices {

	/**
	 * @return
	 */
	List<ModelSpecification> getAllModels();

	/**
	 * @param id
	 * @return
	 */
	ModelSpecification getModel(Long id);

}
