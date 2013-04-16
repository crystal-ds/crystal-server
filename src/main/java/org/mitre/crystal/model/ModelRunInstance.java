/**
 * POJO implementation of a run. 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	
	private Date timestamp;
	
	
	public ModelRunInstance(){
		setTimestamp(new Date());
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	/*
	 * @return the inputValues
	 */
	@Column(name="input_json_value")
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="input_name")
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
	@Column(name="output_json_value")
	@ElementCollection(fetch=FetchType.EAGER)
	@MapKeyColumn(name="output_name")
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


	/**
	 * @return the timestamp
	 */
	@Basic
	@Column(name="time_stamp")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTimestamp() {
		return timestamp;
	}


	/**
	 * @param timestamp the timestamp to set
	 */

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	//this makes the DB work....WHY?
	//Work around for JPA empty object creation
//	@Basic
//	@Column(name = "dummy")
//	public String getDummy(){
//		return "dummy2";
//	}
//	public void setDummy(String dummy){
//	}

	
}
