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
	 * @return
	 */
	List<ScoringModel> getAllModels();

	/**
	 * @param id
	 * @return
	 */
	ScoringModel getModel(long id);
	
	public void removeModel(ScoringModel model);
	
	public ScoringModel saveModel(ScoringModel model);

}
