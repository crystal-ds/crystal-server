/**
 * 
 */
package org.mitre.crystal.model.EMEImpl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.impl.DefaultPrettyPrinter;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
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
	}

	@Override
	public void runModel(ModelRunInstance mri) {
		Map<String, String> outputValues = mri.getOutputValues();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();

		StringWriter sw = new StringWriter();
		// //Writer w = new PrintW;
		//JsonGenerator jsonGenerator;
		Map<String, Integer> out = new HashMap<String, Integer>();
			//jsonGenerator = mapper.getJsonFactory().createJsonGenerator(sw);
			//jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
			//
			//jsonGenerator.writeStartObject();
			for (int i = 0; i < 10; i++) {
				// on.put("Value" + i , i);
				//jsonGenerator.writeObjectField("Value" + i, i);
				//out.put("Value" + i, i);
				outputValues.put("Value" + i, Integer.toString(i));
			}

			//jsonGenerator.writeEndObject();
			//jsonGenerator.flush();
			
			//mapper.writeValue(sw, out);
			String vals = sw.toString();
			//outputValues.put("Outputs", vals);
			mri.setOutputValues(outputValues);

	}

	/**
	 * 
	 */
	@PostConstruct
	public void init() {

		this.setName("ImABigDummy");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEASE DON'T USE");
		this.setId((long) 1);

		InputNode ipn1 = new InputNode();
		ipn1.setName("InputNode1");
		ipn1.setType(InputType.CHECKBOX);
		Map<String, String> m1 = ipn1.getProperties();
		m1.put("checked", "true");
		ipn1.setProperties(m1);
		Map<String, String> m = new HashMap<String, String>();
		m.put("key1", "Value1");
		ipn1.setProperties(m);

		InputNode ipn2 = new InputNode();
		ipn2.setName("InputNode2");
		ipn2.setType(InputType.SIMPLE);
		Map<String, String> m2 = new HashMap<String, String>();
		m.put("min", "1");
		m.put("max", "5");
		m.put("value", "3");
		ipn2.setProperties(m);

		InputNode ipn3 = new InputNode();
		ipn3.setName("InputNode3");
		ipn3.setType(InputType.RANGESLIDER);
		Map<String, String> m3 = new HashMap<String, String>();
		m.put("min", "0");
		m.put("max", "10");
		m.put("low", "5");
		m.put("high:", "7");
		ipn3.setProperties(m);

		ArrayList<InputNode> inputList = new ArrayList<InputNode>();
		inputList.add(ipn1);
		inputList.add(ipn2);
		inputList.add(ipn3);
		this.setInputs(inputList);

	}

}
