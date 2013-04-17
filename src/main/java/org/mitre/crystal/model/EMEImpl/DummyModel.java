/**
 * 
 */
package org.mitre.crystal.model.EMEImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.InputType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 * 
 */
public class DummyModel extends RunnableModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mitre.crystal.model.ModelSpecification#run(org.mitre.crystal.model
	 * .ModelRunInstance)
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 63069295459169859L;

	public DummyModel() {
		this.setModelSpec(new ModelSpecification());
		this.setName("ImABigDummy");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEASE DON'T USE");
	}

	@Override
	public void runModel(ModelRunInstance mri) {
		Map<String, String> outputValues = mri.getOutputValues();
			for (int i = 0; i < 10; i++) {
				outputValues.put("Value" + i, Integer.toString(i));
			}
			mri.setOutputValues(outputValues);
	}

	/**
	 * 
	 */
	@PostConstruct
	public void init() {

	

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
