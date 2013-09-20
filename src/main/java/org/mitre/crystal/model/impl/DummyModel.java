/**
 * 
 */
package org.mitre.crystal.model.impl;

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
 * This is an example of how to create a model. Spring injects these into the InMemoryModelRepository.
 */
public class DummyModel extends RunnableModel {

	private static final long serialVersionUID = 63069295459169859L;

	public DummyModel() {
		this.setModelSpec(new ModelSpecification());
		this.setName("Personnel Recovery");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEASE DON'T USE");
	}

	@Override
	public void runModel(ModelRunInstance mri) {
		final Map<String, String> outputValues = mri.getOutputValues();
		for (int i = 0; i < 10; i++) {
			outputValues.put("Value" + i, Integer.toString(i));
		}
		mri.setOutputValues(outputValues);
	}

	/**
	 * We use the PostConstruct to add make sure the code is run after object creation
	 */
	@PostConstruct
	public void init() {
		final InputNode ipn1 = new InputNode();
		ipn1.setName("Number of isolated personnel");
		ipn1.setType(InputType.INTEGER);
		final Map<String, String> m1 = ipn1.getProperties();
		m1.put("min", "1");
		m1.put("max", "100");
		m1.put("value", "3");
		ipn1.setProperties(m1);

		final InputNode ipn2 = new InputNode();
		ipn2.setName("Number of assets available");
		ipn2.setType(InputType.INTEGER);
		final Map<String, String> m = new HashMap<String, String>();
		m.put("min", "1");
		m.put("max", "100");
		m.put("value", "3");
		ipn2.setProperties(m);

		final InputNode ipn3 = new InputNode();
		ipn3.setName("Number of beacons with locations");
		ipn3.setType(InputType.INTEGER);
		final Map<String, String> m3 = new HashMap<String, String>();
		m3.put("min", "0");
		m3.put("max", "10");
		m3.put("value", "7");
		ipn3.setProperties(m3);

		final ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		this.setInputs(inputList);
	}
}
