/**
 * POJO implementation of a run. 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "model_run_instance")
public class ModelRunInstance implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8422227697572962705L;
	//private Long runId;
	private Long id;
	
//	private ModelRunInputValues inputValues;
	//name to Json for model inputs
	private Map<String,String> inputValues;
//	private ModelRunOutputValues outputValues;
	//String to json for output values; 
	private Map<String, String> outputValues;
	

	
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
//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name = "input_value_id")
//	//public ModelRunInputValues getInputValues() {
//		return inputValues;
//	}
//	public void setInputValues(ModelRunInputValues inputValues) {
//		this.inputValues = inputValues;
//	}
//
//	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
//	@JoinColumn(name = "output_value_id")
//	public ModelRunOutputValues getOutputValues() {
//		return outputValues;
//	}
//	public void setOutputValues(ModelRunOutputValues outputValues) {
//		this.outputValues = outputValues;
//	}
	/**
	 * @return the model
	 */
	//TODO figure out how to link the model
//	@ManyToOne 
//	@JoinColumn (name = "model_id")
////	public ModelSpecification getModel() {
////		return model;
////	}
	/*
	 * @return the inputValues
	 */
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="input_name")
	@Column(name="input_json_value")
	@CollectionTable(
			name="model_run_instance_inputs",
			joinColumns=@JoinColumn(name="model_run_instance_id")		
			)
	public Map<String,String> getInputValues() {
		return inputValues;
	}
	/**
	 * @param inputValues the inputValues to set
	 */
	public void setInputValues(Map<String,String> inputValues) {
		this.inputValues = inputValues;
	}
	/**
	 * @return the outputValues
	 */
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="output_name")
	@Column(name="output_json_value")
	@CollectionTable(
			name="model_run_instance_outputs",
			joinColumns=@JoinColumn(name="model_run_instance_id")		
			)
	public Map<String, String> getOutputValues() {
		return outputValues;
	}
	/**
	 * @param outputValues the outputValues to set
	 */
	public void setOutputValues(Map<String, String> outputValues) {
		this.outputValues = outputValues;
	}

	//this makes the DB work....WHY?
	//Work around for JPA empty object creation
	@Basic
	@Column(name = "dummy")
	public String getDummy(){
		return "dummy";
	}
	public void setDummy(String dummy){
	}

	
}
