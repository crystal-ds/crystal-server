/**
 * 
 */
package org.mitre.crystal.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
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
 * This example scoreing model generates scores in a normal distribution. 
 */
public class NormalDistributionModel extends ScoringModel{

	private static final long serialVersionUID = -5319265546736421132L;
	@Override


	public SMBatchJob score(ScoringModelInput vals, BatchJob job) {


		final Random rand = new Random();
		final SMBatchJob sbj = new SMBatchJob();
		final List<ModelRunInstance> l = job.getInstances();
		final List<ScoreRunInstance> scoreList = new ArrayList<ScoreRunInstance>();

		for (final ModelRunInstance modelRunInstance : l) {
			final Map<String, String> m = new HashMap<String, String>();
			m.put("Score", Double.toString(Math.abs(rand.nextGaussian()*100)));
			final ScoreRunInstance sri = new ScoreRunInstance();
			sri.setMriJobInstanceID(modelRunInstance.getId());
			sri.setOutputValues(m);
			sri.setTimestamp(new Date());
			scoreList.add(sri);
		}
		sbj.setInstances(scoreList);
		sbj.setScoringModelID(this.getId());

		return sbj;
	}

	public NormalDistributionModel(){
		this.setModelSpec(new ModelSpecification());
		this.setName("DummyScoringModel");
		this.setDescription("Example scoring model");
	}

	@PostConstruct
	public void init(){

		final InputNode ipn1 = new InputNode();
		ipn1.setName("DummyScoringModelInput_1");
		ipn1.setType(InputType.BOOLEAN);
		final Map<String, String> m1 = ipn1.getProperties();
		m1.put("value", "true");
		ipn1.setProperties(m1);

		final InputNode ipn2 = new InputNode();
		ipn2.setName("DummyScoringModelInput_2");
		ipn2.setType(InputType.FLOAT);
		final Map<String, String> m = new HashMap<String, String>();
		m.put("min", "1");
		m.put("max", "5");
		m.put("value", "3");
		ipn2.setProperties(m);

		final InputNode ipn3 = new InputNode();
		ipn3.setName("DummyScoringModelInput_3");
		ipn3.setType(InputType.RANGE);
		final Map<String, String> m3 = new HashMap<String, String>();
		m3.put("min", "0");
		m3.put("max", "10");
		m3.put("upper", "5");
		m3.put("lower", "7");
		m3.put("value", "5");
		ipn3.setProperties(m3);

		final ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		this.setInputs(inputList);
	}
}
