/**
 * POJO implementation of a run. 
 */
package org.mitre.crystal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "model_run_instance")
public class ModelRunInstance {
	
	
	
	//private Long runId;
	private Long id;
	private ModelSpecification model;
	
	private ModelRunInputValues inputValues;
	private ModelRunOutputValues outputValues;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
//	@Column (name = "batch_id")
//	public Long getRunId() {
//		return runId;
//	}
//	public void setRunId(Long runId) {
//		this.runId = runId;
//	}
	@OneToOne
	@JoinColumn(name = "input_value_id")
	public ModelRunInputValues getInputValues() {
		return inputValues;
	}
	public void setInputValues(ModelRunInputValues inputValues) {
		this.inputValues = inputValues;
	}

	@OneToOne
	@JoinColumn(name = "output_value_id")
	public ModelRunOutputValues getOutputValues() {
		return outputValues;
	}
	public void setOutputValues(ModelRunOutputValues outputValues) {
		this.outputValues = outputValues;
	}
	/**
	 * @return the model
	 */
	//TODO figure out how to link the model
//	@ManyToOne 
//	@JoinColumn (name = "model_id")
////	public ModelSpecificationData getModel() {
////		return model;
////	}
	/**
	 * @param model the model to set
	 */
	public void setModel(ModelSpecification model) {
		this.model = model;
	}
	
	
}
