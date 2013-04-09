/**
 * 
 */
package org.mitre.crystal.service.diversifier;

import java.util.List;

import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 *
 */
public interface InputDiversifier {

	/**
	 * @param model
	 * @param vals
	 * @return
	 */
	List<ModelRunInputValues> diversify(RunnableModel model,
			ModelRunInputValues vals);

}
