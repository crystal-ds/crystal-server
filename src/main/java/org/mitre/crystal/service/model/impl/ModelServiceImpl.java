/**
 * Pass through model service impl
 */
package org.mitre.crystal.service.model.impl;

import java.util.List;

import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.repository.ModelRepository;
import org.mitre.crystal.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service("modelService")
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository modelRepo;
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#createModel(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public RunnableModel saveModel(RunnableModel model) {
		
		return modelRepo.saveModel(model);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getModel(long)
	 */
	@Override
	public RunnableModel getModel(long id) {
		return modelRepo.getModel(id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#removeModel(org.mitre.crystal.model.RunnableModel)
	 */
	@Override
	public void removeModel(RunnableModel modelSpec) {
		modelRepo.removeModel(modelSpec);

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */
	@Override
	public List<RunnableModel> getAllModels() {
		
		return modelRepo.getAllModels();
	}

}
