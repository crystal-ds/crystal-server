package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * When implementing your own Scoreing Model this is the class you will need to extend
 * The key place you will need to interface with your scoreing model is the score method 
 * which will be called with a model specification and a batchjob. 
 * @author tmlewis
 *
 */
public abstract class ScoringModel implements Serializable{

	private static final long serialVersionUID = -65249764050542815L;

	//This is the speficiation that includes the inputs to your scoreing model
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

	/**
	 * @param vals The set of values you wish to score against
	 * @param job The batch job you will be scoreing
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
