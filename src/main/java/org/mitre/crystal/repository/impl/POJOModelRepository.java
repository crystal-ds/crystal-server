/**
 * 
 */
package org.mitre.crystal.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.EMEImpl.DummyModel;
import org.mitre.crystal.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tmlewis
 *
 */
public class POJOModelRepository implements ModelService {

	final Logger log = LoggerFactory.getLogger(JPABatchJobRepository.class);
	private static long modelCounter = 0;
	private static Map <Long,ModelSpecification>mapOfModels;

	/**
	 * 
	 */
	//TODO do I need to make this a singleton?d
	public POJOModelRepository() {
		mapOfModels = new HashMap<Long,ModelSpecification>();
		
		//init map
		saveModel(new DummyModel());
		
		
		
	}
	

	@Override
	public ModelSpecification saveModel(ModelSpecification model) {
		model.setId(modelCounter);
		mapOfModels.put(modelCounter,model);
		modelCounter++;
		return model;
	}


	@Override
	public ModelSpecification getModel(long id) {
		return mapOfModels.get(id);
	}

	@Override
	public void removeModel(ModelSpecification modelSpec) {
		Long id = modelSpec.getId();
		mapOfModels.remove(id);

	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.ModelService#getAllModels()
	 */
	@Override
	public List<ModelSpecification> getAllModels() {

		List<ModelSpecification> modelList = new ArrayList<ModelSpecification>();
		
		
		Set<Entry<Long, ModelSpecification>> entrySet = mapOfModels.entrySet();
		for (Iterator<Entry<Long, ModelSpecification>> iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Long, ModelSpecification> entry = (Entry<Long, ModelSpecification>) iterator
					.next();
			modelList.add(entry.getValue());
		}
		return modelList;	
	}

}
