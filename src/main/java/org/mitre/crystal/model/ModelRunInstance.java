/**
 * POJO implementation of a run. 
 */
package org.mitre.crystal.model;


/**
 * @author tmlewis
 *
 */
public class ModelRunInstance {
	
	//TODO Database Integration
	
	private Long runId;
	private Long modelid;
	private Long batchId;
	private ModelRunInputValues inputValues;
	private ModelRunOutputValues outputValues;
	
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
	public Long getModelid() {
		return modelid;
	}
	public void setModelid(Long modelid) {
		this.modelid = modelid;
	}
	public Long getBatchId() {
		return batchId;
	}
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}
	public ModelRunInputValues getInputValues() {
		return inputValues;
	}
	public void setInputValues(ModelRunInputValues inputValues) {
		this.inputValues = inputValues;
	}
	public ModelRunOutputValues getOutputValues() {
		return outputValues;
	}
	public void setOutputValues(ModelRunOutputValues outputValues) {
		this.outputValues = outputValues;
	}
	
	
}
