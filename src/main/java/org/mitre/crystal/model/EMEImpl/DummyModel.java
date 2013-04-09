/**
 * 
 */
package org.mitre.crystal.model.EMEImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.InputType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelRunOutputValues;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.model.RunnableModel;

import com.sun.tools.javac.util.List;

/**
 * @author tmlewis
 *
 */
public class DummyModel extends RunnableModel {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.model.ModelSpecification#run(org.mitre.crystal.model.ModelRunInstance)
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 63069295459169859L;

	public DummyModel(){
		this.setModelSpec(new ModelSpecification());		
	}
	
	
	@Override
	public  void runModel(ModelRunInstance mri) {
		ModelRunOutputValues outputValues = new ModelRunOutputValues();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		for(int i = 0; i< 100; i++){
			on.put("Value" + i , i);
		}
		HashMap<String, String> outputs = new HashMap<String, String>();
			outputs.put("Outputs", on.toString());
	
		outputValues.setOutputs(outputs);
	}

	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		
		this.setName("ImABigDummy");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEAES DONT' USE");
		this.setId((long)8675309);
		InputNode ipn1 = new InputNode();
		ipn1.setName("Lawyers");
		ipn1.setType(InputType.CHECKBOX);
		Map<String,String> m = new HashMap<String, String>();
		m.put("key1", "Value1");
		ipn1.setProperties(m);

		InputNode ipn2 = new InputNode();
		ipn2.setName("Guns");
		ipn2.setType(InputType.SIMPLE);
		Map<String,String> m2 = new HashMap<String, String>();
		m.put("key2", "Value2");
		ipn2.setProperties(m);
		
		
		InputNode ipn3 = new InputNode();
		ipn3.setName("Money");
		ipn3.setType(InputType.RANGESLIDER);
		Map<String,String> m3 = new HashMap<String, String>();
		m.put("key3", "Value3");
		ipn3.setProperties(m);
		
		
		
		ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		this.setInputs(inputList);
		
	}

}
