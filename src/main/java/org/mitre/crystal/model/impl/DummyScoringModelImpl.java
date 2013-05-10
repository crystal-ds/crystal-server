/**
 * 
 */
package org.mitre.crystal.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoringModel;
import org.mitre.crystal.model.ScoringModelInput;

/**
 * @author tmlewis
 *
 */
public class DummyScoringModelImpl extends ScoringModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1341457363059579543L;

	@Override
	public SMBatchJob score(ScoringModelInput vals, BatchJob job) {
		SMBatchJob sbj = new SMBatchJob();
		
		Map<String, String> m = new HashMap<>();
		m.put("Scrore1", "100");
		m.put("Score2", "25");
		List<ModelRunInstance> l = job.getInstances();
		List<ModelRunInstance> scoreList = new ArrayList<>();
		for (ModelRunInstance modelRunInstance : l) {
			ModelRunInstance mri = new ModelRunInstance();
			//match up the ids so they can be synced up
			mri.setId(modelRunInstance.getId()); 
			mri.setOutputValues(m);
			scoreList.add(mri);
		}
		sbj.setInstances(scoreList);
		
	return sbj;
	}
}
