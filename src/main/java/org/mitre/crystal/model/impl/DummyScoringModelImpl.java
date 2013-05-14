/**
 * 
 */
package org.mitre.crystal.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mitre.crystal.model.BatchJob;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.InputType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.SMBatchJob;
import org.mitre.crystal.model.ScoreRunInstance;
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
		
		Map<String, String> m = new HashMap<String, String>();
		m.put("Score1", "100");
		m.put("Score2", "25");
		List<ModelRunInstance> l = job.getInstances();
		List<ScoreRunInstance> scoreList = new ArrayList<ScoreRunInstance>();
		
		for (ModelRunInstance modelRunInstance : l) {
			//ModelRunInstance mri = new ModelRunInstance();
			//match up the ids so they can be synced up
			//mri.setId(modelRunInstance.getId());
			//mri.);
			//mri.setInputValues(modelRunInstance.getInputValues());
			//mri.setOutputValues(m);
			//scoreList.add(mri);
			ScoreRunInstance sri = new ScoreRunInstance();
			sri.setMriJobInstanceID(modelRunInstance.getId());
			sri.setOutputValues(m);
			scoreList.add(sri);
		}
		sbj.setInstances(scoreList);
		sbj.setScoringModelID(this.getId());
		
	return sbj;
	}
	
	public DummyScoringModelImpl(){

	}
	
	@PostConstruct
	public void init(){

		
		this.setModelSpec(new ModelSpecification());
		this.setName("ScoreThisModel");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEASE DON'T USE");

		InputNode ipn1 = new InputNode();
		ipn1.setName("InputNode1");
		ipn1.setType(InputType.CHECKBOX);
		Map<String, String> m1 = ipn1.getProperties();
		m1.put("checked", "true");
		ipn1.setProperties(m1);
		ipn1.setProperties(m1);

		InputNode ipn2 = new InputNode();
		ipn2.setName("InputNode2");
		ipn2.setType(InputType.SIMPLE);
		Map<String, String> m = new HashMap<String, String>();
		m.put("min", "1");
		m.put("max", "5");
		m.put("value", "3");
		ipn2.setProperties(m);

		InputNode ipn3 = new InputNode();
		ipn3.setName("InputNode3");
		ipn3.setType(InputType.RANGESLIDER);
		Map<String, String> m3 = new HashMap<String, String>();
		m3.put("min", "0");
		m3.put("max", "10");
		m3.put("low", "5");
		m3.put("high", "7");
		ipn3.setProperties(m3);

		ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		this.setInputs(inputList);
	}
}
