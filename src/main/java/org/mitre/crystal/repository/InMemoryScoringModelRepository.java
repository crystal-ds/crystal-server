/**
 * 
 */
package org.mitre.crystal.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.ScoringModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author tmlewis
 *
 */
@Repository("inMemoryScoreingModelRepository")
public class InMemoryScoringModelRepository implements ScoringModelRepository {
	final Logger log = LoggerFactory.getLogger(InMemoryScoringModelRepository.class);
	private Map<Long, ScoringModel> mapOfModels;
	
	
	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringModelRepository#getModel(long)
	 */
	@Override
	public ScoringModel getModel(long id) {
		return mapOfModels.get(id);
		
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringModelRepository#getAllModels()
	 */
	@Override
	public List<ScoringModel> getAllModels() {
		ArrayList<ScoringModel> l = new ArrayList<ScoringModel>(mapOfModels.values());
		return l;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringModelRepository#saveModel(org.mitre.crystal.model.ScoringModel)
	 */
	@Override
	public ScoringModel saveModel(ScoringModel model) {
		mapOfModels.put(model.getId(), model);
		return model;
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.repository.ScoringModelRepository#removeModel(org.mitre.crystal.model.ScoringModel)
	 */
	@Override
	public void removeModel(ScoringModel model) {
		mapOfModels.remove(model.getId());

	}

}
