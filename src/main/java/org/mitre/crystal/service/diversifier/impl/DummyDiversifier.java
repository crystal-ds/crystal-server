/**
 * 
 */
package org.mitre.crystal.service.diversifier.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mitre.crystal.model.RunnableModel;
import org.mitre.crystal.service.diversifier.InputDiversifier;
import org.springframework.stereotype.Service;

/**
 * @author tmlewis
 *
 */
@Service
public class DummyDiversifier implements InputDiversifier {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.service.diversifier.InputDiversifier#diversify(org.mitre.crystal.model.ModelSpecification, org.mitre.crystal.model.ModelRunInputValues)
	 */
	@Override
	public List<Map<String, String>> diversify(RunnableModel model,
			Map<String,String> vals) {
		
			List<Map <String,String>> l = new ArrayList<Map<String,String>>();
			l.add(vals);
		return l;
	}
}
