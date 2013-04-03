/**
 * 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author tmlewis
 *
 */
@NamedQueries({
	@NamedQuery(name = "ModelSpecification.getAll", query="select m from ModelSpecification m")
})

public abstract class ModelSpecification implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610853539172489884L;
	private ModelSpecificationData modelSpec;

	public String getName() {
		return modelSpec.getName();
	}

	public String getDescription() {
		return modelSpec.getDescription();
	}

	public Long getId() {
		return modelSpec.getId();
	}

	public List<InputNode> getInputs() {
		return modelSpec.getInputs();
	}

	public void setName(String name) {
		modelSpec.setName(name);
	}

	public void setDescription(String description) {
		modelSpec.setDescription(description);
	}

	public void setId(Long id) {
		modelSpec.setId(id);
	}

	public void setInputs(List<InputNode> inputs) {
		modelSpec.setInputs(inputs);
	}

	public void runModel(ModelRunInstance mri){
		
	}

}
