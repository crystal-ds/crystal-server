/**
 * POJO implementation of a run. 
 */
package org.mitre.crystal.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "model_runs")
public class ModelRunInstance {
	
	
	
	private Long runId;
	private ModelSpecification model;
	
	private ModelRunInputValues inputValues;
	private ModelRunOutputValues outputValues;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	@Basic
	@Column(name = "input_values")
	@JoinColumn(name = "id")
	public ModelRunInputValues getInputValues() {
		return inputValues;
	}
	public void setInputValues(ModelRunInputValues inputValues) {
		this.inputValues = inputValues;
	}
	@Basic
	@Column(name = "output_values")
	@JoinColumn(name = "id")
	public ModelRunOutputValues getOutputValues() {
		return outputValues;
	}
	public void setOutputValues(ModelRunOutputValues outputValues) {
		this.outputValues = outputValues;
	}
	/**
	 * @return the model
	 */
	@ManyToOne 
	@JoinColumn (name = "model_id")
	public ModelSpecification getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(ModelSpecification model) {
		this.model = model;
	}
	
	
}
