/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.ScoringModel;

/**
 * @author tmlewis
 *
 */
public interface ScoringModelService {

	/**
	 * @return All models
	 */
	List<ScoringModel> getAllModels();

	/**
	 * @param id The id of the Scoring model you want to get
	 * @return The requested model
	 */
	ScoringModel getModel(long id);
	
	/**
	 * 
	 * @param model Scoreing model you wish to remove
	 */
	public void removeModel(ScoringModel model);
	
	/**
	 * 
	 * @param model The model you wish to save
	 * @return The saved model
	 */
	public ScoringModel saveModel(ScoringModel model);

}
