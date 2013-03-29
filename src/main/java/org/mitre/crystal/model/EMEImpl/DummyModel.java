/**
 * 
 */
package org.mitre.crystal.model.EMEImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.InputType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelRunOutputValues;
import org.mitre.crystal.model.ModelSpecification;

/**
 * @author tmlewis
 *
 */
public class DummyModel extends ModelSpecification {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.model.ModelSpecification#run(org.mitre.crystal.model.ModelRunInstance)
	 */
	
	public DummyModel(){
		init();
		
	}
	
	
	@Override
	public  void run(ModelRunInstance mri) {
		ModelRunOutputValues outputValues = new ModelRunOutputValues();
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		for(int i = 0; i< 100; i++){
			on.put("Value" + i , i);
		}
		HashMap<String, JsonNode> outputs = new HashMap<String, JsonNode>();
		try {
			outputs.put("Outputs", mapper.readTree(on.toString()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		outputValues.setOutputs(outputs);
	}

	/**
	 * 
	 */
	private void init() {
		this.setName("ImABigDummy");
		this.setDescription("This is a dummy model since the real model doesn't exist yet. PLEAES DONT' USE");
		this.setId((long)8675309);
		
		InputNode ipn1 = new InputNode();
		ipn1.setName("Lawyers");
		ipn1.setType(InputType.CHECKBOX);
		
		InputNode ipn2 = new InputNode();
		ipn1.setName("Guns");
		ipn1.setType(InputType.SIMPLE);
		
		InputNode ipn3 = new InputNode();
		ipn1.setName("Money");
		ipn1.setType(InputType.RANGESLIDER);
		
		
		
		Map<String,InputNode> stringToInputpuNode = new HashMap<String, InputNode>();
		
		stringToInputpuNode.put("Warren", ipn1);
		stringToInputpuNode.put("Zevon", ipn2);
		stringToInputpuNode.put("needs", ipn3);
		
	}

}
