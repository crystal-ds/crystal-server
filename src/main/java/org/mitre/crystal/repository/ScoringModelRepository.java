/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.ScoringModel;

/**
 * @author tmlewis
 *
 */
public interface ScoringModelRepository {

	/**
	 * @param id
	 * @return
	 */
	ScoringModel getModel(long id);

	/**
	 * @return
	 */
	List<ScoringModel> getAllModels();

	/**
	 * @param model
	 * @return
	 */
	ScoringModel saveModel(ScoringModel model);

	/**
	 * @param model
	 */
	void removeModel(ScoringModel model);

}
