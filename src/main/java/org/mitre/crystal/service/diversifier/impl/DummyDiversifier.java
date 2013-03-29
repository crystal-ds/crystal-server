/**
 * 
 */
package org.mitre.crystal.service.diversifier.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecification;
import org.mitre.crystal.service.diversifier.InputDiversifier;

/**
 * @author tmlewis
 *
 */
public class DummyDiversifier implements InputDiversifier {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.diversifier.InputDiversifier#diversify(org.mitre.crystal.model.ModelSpecification, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public List<ModelRunInputValues> diversify(ModelSpecification model,
			ModelRunInputValues vals) {
		
		
		ArrayList<ModelRunInputValues> l = new ArrayList<ModelRunInputValues>();
		Map<String, InputNode> inputs = model.getInputs();
		Set<Entry<String, InputNode>> inputNames = inputs.entrySet();
		int i = 0;
		for (Entry<String, InputNode> entry : inputNames) {
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode on = mapper.createObjectNode();
			Map<String, JsonNode> map = new HashMap<String, JsonNode>();
			map.put("Test" + i, on.get("value"));
			ModelRunInputValues mriv = new ModelRunInputValues();
			mriv.setInputs(map);
			
			on.put("value", i);
			map.put(entry.getKey(), on.get("value"));
			l.add(mriv);
			i++;
		}
		return l;
	}
}
