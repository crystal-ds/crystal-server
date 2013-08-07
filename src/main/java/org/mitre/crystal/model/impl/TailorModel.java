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
import org.mitre.crystal.model.InputVariableType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunnableModel;

/**
 * @author tmlewis
 * 
 */
public class TailorModel extends RunnableModel {

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
	private static final long serialVersionUID = 63069294821569859L;

	public TailorModel() {
		this.setModelSpec(new ModelSpecification());
		this.setName("Tailor");
		this.setDescription("This is a stubbed out model for work with Tailor integration");
	}

	@Override
	public void runModel(ModelRunInstance mri) {
		//TODO FILL THIS OUT AFTER CHANGES TO DATA TYPES
		//COST  =  100*units * (1.1 * isolated / BEACONS)
		//CASUALTIES =   ((10 * ISOLATED)/BEACONS)   /UNITS 
		//TIME Air = 1 Ground = 2 Sea = 3
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
		ipn1.setName("Miles of visibility");
		ipn1.setType(InputType.INTEGER);
		ipn1.setVariableType(InputVariableType.INITIAL_CONDITION);
		Map<String, String> m1 = ipn1.getProperties();
		m1.put("min", "0");
		m1.put("max", "100");
		//Default
		m1.put("value", "10");
		ipn1.setProperties(m1);
		
		InputNode ipn1a = new InputNode();
		ipn1a.setName("Wind Speed (mph)");
		ipn1a.setType(InputType.INTEGER);
		ipn1a.setVariableType(InputVariableType.INITIAL_CONDITION);
		Map<String, String> m1a = ipn1.getProperties();
		m1a.put("min", "0");
		m1a.put("max", "50");
		//Default
		m1a.put("value", "10");
		ipn1.setProperties(m1a);

		InputNode ipn2 = new InputNode();
		ipn2.setName("Enemy Troop Concentration");
		ipn2.setType(InputType.INTEGER);
		ipn2.setVariableType(InputVariableType.INTERMEDIARY_VARIABLE);
		Map<String, String> m1b = new HashMap<String, String>();
		m1b.put("min", "10");
		m1b.put("max", "1000");
		//Default
		m1b.put("value", "10");
		ipn2.setProperties(m1b);

		InputNode ipn3 = new InputNode();
		ipn3.setName("Available Friendly Forces");
		ipn3.setType(InputType.INTEGER);
		ipn3.setVariableType(InputVariableType.COURSE_OF_ACTION);
		Map<String, String> m3 = new HashMap<String, String>();
		m3.put("min", "0");
		m3.put("max", "10000");
		//Default
		m3.put("value", "1");
		ipn3.setProperties(m3);
		
		
		
		InputNode ipn4 = new InputNode();
		ipn4.setName("Enemy Weapon Capability");
		ipn4.setType(InputType.INTEGER);
		ipn4.setVariableType(InputVariableType.INTERMEDIARY_VARIABLE);
		Map<String, String> m4 = new HashMap<String, String>();
		m4.put("min", "0");
		m4.put("max", "100");
		//Default
		m4.put("value", "1");
		ipn4.setProperties(m4);

		ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		inputList.add(ipn4);
		this.setInputs(inputList);

	}

}
