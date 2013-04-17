/**
 * 
 */
package org.mitre.crystal.repository.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.repository.ModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author tmlewis
 *
 */

@Repository("inMemoryModelRepository")
public class InMemoryModelRepository implements ModelRepository {

	final Logger log = LoggerFactory.getLogger(InMemoryModelRepository.class);

	private  Map <Long,RunnableModel>mapOfModels;

	
	public  Map<Long, RunnableModel> getMapOfModels() {
		return mapOfModels;
	}

	
	public  void setMapOfModels(Map<Long, RunnableModel> mapOfModels) {
		this.mapOfModels = mapOfModels;
	}

	public InMemoryModelRepository() {
		
	}
	

	@Override
	public RunnableModel saveModel(RunnableModel model) {
		mapOfModels.put(model.getId(), model);
		return model;
	}


	@Override
	public RunnableModel getModel(long id) {
		return mapOfModels.get(id);
	}

	@Override
	public void removeModel(RunnableModel modelSpec) {
		Long id = modelSpec.getId();
		mapOfModels.remove(id);

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */
	@Override
	public List<RunnableModel> getAllModels() {

		List<RunnableModel> modelList = new ArrayList<RunnableModel>();
		
		
		Set<Entry<Long, RunnableModel>> entrySet = mapOfModels.entrySet();
		for (Iterator<Entry<Long, RunnableModel>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Long, RunnableModel> entry = (Entry<Long, RunnableModel>) iterator
					.next();
			modelList.add(entry.getValue());
		}
		return modelList;	
	}

}
