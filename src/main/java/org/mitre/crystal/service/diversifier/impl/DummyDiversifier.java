/**
 * 
 */
package org.mitre.crystal.service.diversifier.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.ModelRunInputValues;
import org.mitre.crystal.model.ModelSpecificationData;
import org.mitre.crystal.service.diversifier.InputDiversifier;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service
public class DummyDiversifier implements InputDiversifier {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.diversifier.InputDiversifier#diversify(org.mitre.crystal.model.ModelSpecificationData, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public List<ModelRunInputValues> diversify(ModelSpecificationData model,
			ModelRunInputValues vals) {
		
		
		ArrayList<ModelRunInputValues> l = new ArrayList<ModelRunInputValues>();
		int i = 0;
		List<InputNode> inputs = model.getInputs();
		//go over all the inputs
		for (Iterator<InputNode> iterator = inputs.iterator(); iterator.hasNext();) {
			InputNode inputNode = iterator.next();
			Map<String, String> prop = inputNode.getProperties();
			Set<Entry<String,String>> s = prop.entrySet();
			//go over all the properties for each input
			Map<String, String> m = new HashMap<String, String>();
			for (Iterator<Entry<String, String>> iterator2 = s.iterator(); iterator2.hasNext();) {
				Entry<String, String> entry = iterator2
						.next();
				m.put(entry.getKey(), entry.getValue() +i);

				
			}
			ModelRunInputValues mriv = new ModelRunInputValues();
			mriv.setInputs(m);
			//TODO
//			ObjectMapper mapper = new ObjectMapper();
//			ObjectNode on = mapper.createObjectNode();
//			Map<String, JsonNode> map = new HashMap<String, JsonNode>();
//			map.put("Test" + i, on.get("value"));
//			mriv.setInputs(map);
//			
//			on.put("value", i);
//			map.put(inputNode. on.get("value"));
//			l.add(mriv);
			i++;
		}
		return l;
	}
}
