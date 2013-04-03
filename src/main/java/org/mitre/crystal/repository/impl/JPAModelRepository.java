/**
 * 
 */
package org.mitre.crystal.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmlewis
 *
 */
public class JPAModelRepository implements ModelService {

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);
	@PersistenceContext
	private EntityManager manager;

	@Override
	public ModelSpecification saveModel(ModelSpecification model) {
		if(getModel(model.getId()) != null){
			manager.persist(model);
			manager.flush();
		}
		else{
			manager.merge(model);
			manager.flush();
		}
		return model;
	}


	@Override
	public ModelSpecification getModel(long id) {
		return manager.find(ModelSpecification.class, id);
	}

	@Override
	public void removeModel(ModelSpecification modelSpec) {
		ModelSpecification model = getModel(modelSpec.getId());
		if(model != null){
			manager.remove(model);
		}

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */
	@Override
	public List<ModelSpecification> getAllModels() {
		TypedQuery<ModelSpecification> query = manager.createNamedQuery("ModelSpecification.getAll", ModelSpecification.class);
		return query.getResultList();
	}
}
