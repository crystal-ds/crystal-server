/**
 * 
 */
package org.mitre.crystal.service.impl;

import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.repository.ModelRepository;
import org.mitre.crystal.service.ModelService;

/**
 * @author tmlewis
 *
 */
public class ModelServiceImpl implements ModelService {

	private ModelRepository modelRepo;
	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#createModel(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public ModelSpecification saveModel(ModelSpecification model) {
		
		return modelRepo.saveModel(model);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getModel(long)
	 */
	@Override
	public ModelSpecification getModel(long id) {
		// TODO Auto-generated method stub
		return modelRepo.getModel(id);
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#removeModel(org.mitre.crystal.model.ModelSpecification)
	 */
	@Override
	public void removeModel(ModelSpecification modelSpec) {
		modelRepo.removeModel(modelSpec);

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */
	@Override
	public List<ModelSpecification> getAllModels() {
		// TODO Auto-generated method stub
		return modelRepo.getAllModels();
	}

}
