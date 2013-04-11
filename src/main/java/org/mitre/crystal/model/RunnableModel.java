/**
 * 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author tmlewis
 *
 */


public abstract class RunnableModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2610853539172489884L;
	private ModelSpecification modelSpec;
	@JsonIgnore
	public ModelSpecification getModelSpec() {
		return modelSpec;
	}

	public void setModelSpec(ModelSpecification modelSpec) {
		this.modelSpec = modelSpec;
	}

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

	public abstract void runModel(ModelRunInstance mri);

}
