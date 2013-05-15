/**
 * 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author tmlewis
 *
 */
public abstract class ScoringModel implements Serializable{


	private ModelSpecification modelSpec;
	public String getName() {
		return modelSpec.getName();
	}



	public void setName(String name) {
		modelSpec.setName(name);
	}



	public String getDescription() {
		return modelSpec.getDescription();
	}



	public void setDescription(String description) {
		modelSpec.setDescription(description);
	}



	public Long getId() {
		return modelSpec.getId();
	}


	public void setId(Long id) {
		modelSpec.setId(id);
	}



	public List<InputNode> getInputs() {
		return modelSpec.getInputs();
	}



	public void setInputs(List<InputNode> inputs) {
		modelSpec.setInputs(inputs);
	}



	private static final long serialVersionUID = -65249764050542815L;



	/**
	 * @param vals
	 * @param job
	 * @return
	 */
	public SMBatchJob score(ScoringModelInput vals, BatchJob job) {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * @return the modelSpec
	 */
	@JsonIgnore
	public ModelSpecification getModelSpec() {
		return modelSpec;
	}



	/**
	 * @param modelSpec the modelSpec to set
	 */
	public void setModelSpec(ModelSpecification modelSpec) {
		this.modelSpec = modelSpec;
	}

}
