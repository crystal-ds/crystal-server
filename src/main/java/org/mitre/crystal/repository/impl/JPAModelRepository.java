/**
 * 
 */
package org.mitre.crystal.repository.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mitre.crystal.model.ModelSpecificationData;
import org.mitre.crystal.repository.ModelRepository;
import org.mitre.crystal.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author tmlewis
 *
 */
@Entity
@Repository("jpaModelRepository")
public class JPAModelRepository implements ModelRepository{

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);
	@PersistenceContext
	private EntityManager manager;

	public ModelSpecificationData saveModel(ModelSpecificationData model) {
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



	public ModelSpecificationData getModel(long id) {
		return manager.find(ModelSpecificationData.class, id);
	}


	public void removeModel(ModelSpecificationData modelSpec) {
		ModelSpecificationData model = getModel(modelSpec.getId());
		if(model != null){
			manager.remove(model);
		}

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */

	public List<ModelSpecificationData> getAllModels() {
		TypedQuery<ModelSpecificationData> query = manager.createNamedQuery("ModelSpecificationData.getAll", ModelSpecificationData.class);
		return query.getResultList();
	}
}
