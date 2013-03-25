/**
 * 
 */
package org.mitre.crystal.model.EMEImpl;

import java.util.HashMap;
import java.util.Map;

import org.mitre.crystal.model.InputNode;
import org.mitre.crystal.model.InputType;
import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;

/**
 * @author tmlewis
 *
 */
public class DummyModel extends ModelSpecification {

	/* (non-Javadoc)
	 * @see org.mitre.crystal.model.ModelSpecification#run(org.mitre.crystal.model.ModelRunInstance)
	 */
	@Override
	public void run(ModelRunInstance mri) {
		init();
		

	}

	/**
	 * 
	 */
	private void init() {
		this.setName("ImABigDummy");
		this.setDescription("Thisis a dummy model since the real model doesn't exist yet. PLEAES DONT' USE");
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
		
		
		
		Map<String,InputNode> stringToInputpuNode = new HashMap();
		
		stringToInputpuNode.put("Warren", ipn1);
		stringToInputpuNode.put("Zevon", ipn2);
		stringToInputpuNode.put("needs", ipn3);
		
	}

}
