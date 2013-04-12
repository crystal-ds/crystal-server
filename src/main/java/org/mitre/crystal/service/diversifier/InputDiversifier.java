/**
 * 
 */
package org.mitre.crystal.service.diversifier;

import java.util.List;
import java.util.Map;

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
	List<Map<String,String>> diversify(RunnableModel model,
			Map<String,String> vals);

}
