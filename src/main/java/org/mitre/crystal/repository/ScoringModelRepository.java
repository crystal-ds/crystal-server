package org.mitre.crystal.repository;

import java.util.List;

import org.mitre.crystal.model.ScoringModel;

/**
 * @author tmlewis
 *
 */
public interface ScoringModelRepository {

	/**
	 * @param id Id of the scoreing model you want to get
	 * @return A working scoreing model
	 */
	ScoringModel getModel(long id);

	/**
	 * @return A list of all available Scoreing Models
	 */
	List<ScoringModel> getAllModels();

	/**
	 * @param model The model you want to save to the repository
	 * @return The saved scoreing model compelete with ID
	 */
	ScoringModel saveModel(ScoringModel model);

	/**
	 * @param model Model to remove from the repository
	 */
	void removeModel(ScoringModel model);

}
