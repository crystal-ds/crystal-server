/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.InputSpecification;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunGroup;
import org.springframework.http.HttpStatus;

import com.google.gson.JsonObject;

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
