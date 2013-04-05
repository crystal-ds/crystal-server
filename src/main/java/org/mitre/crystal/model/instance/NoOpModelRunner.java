/**
 * 
 */
package org.mitre.crystal.model.instance;

import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.ModelSpecification;

/**
 * @author tmlewis
 *
 */
public class NoOpModelRunner extends ModelSpecification {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6138254372343440725L;

	/**
	 * 
	 */
	public NoOpModelRunner() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.mitre.crystal.model.ModelSpecification#runModel(org.mitre.crystal.model.ModelRunInstance)
	 */
	@Override
	public void runModel(ModelRunInstance mri) {
		// No Ops Allowed
	}

}
