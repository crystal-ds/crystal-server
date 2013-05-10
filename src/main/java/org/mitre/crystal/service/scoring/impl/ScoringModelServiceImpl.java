/**
 * 
 */
package org.mitre.crystal.service.scoring.impl;

import java.util.List;


import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.repository.ScoringModelRepository;
import org.mitre.crystal.service.ScoringModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service("scoreingModelService")
public class ScoringModelServiceImpl implements ScoringModelService {
	
	Logger log = LoggerFactory.getLogger(ScoringModelServiceImpl.class);
	
	@Autowired
	ScoringModelRepository modelRepo;
	
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringModelService#getAllModels()
	 */
	@Override
	public List<ScoringModel> getAllModels() {
		log.info("Saving model in the service layer");
		return modelRepo.getAllModels();
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringModelService#getModel(long)
	 */
	@Override
	public ScoringModel getModel(long id) {
		log.info("Getting model "+ id + " in service layer");
		return modelRepo.getModel(id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringModelService#removeModel(org.mitre.crystal.model.ScoringModel)
	 */
	@Override
	public void removeModel(ScoringModel model) {
		log.info("removing model " + model.getId() + " in service layer");
		modelRepo.removeModel(model);
		
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ScoringModelService#saveModel(org.mitre.crystal.model.ScoringModel)
	 */
	@Override
	public ScoringModel saveModel(ScoringModel model) {
		log.info("Saveing model " + model.getId() + " in service layer");
		return modelRepo.saveModel(model);
	}

}
