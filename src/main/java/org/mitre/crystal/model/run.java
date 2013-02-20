/**
 * POJO implementation of a run. A run contains the results of both the EME and the SME.
 */
package org.mitre.crystal.model;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;

/**
 * @author tmlewis
 *
 */
public class run {
	
	private long id;
	private Map<String,JsonObject> modelInputs;
	private Map<String,JsonObject> scoreOutputs;
	public run(long id, Map modelInputs, Map scoreOutputs) {
		super();
		this.id = id;
		this.modelInputs = modelInputs;
		this.scoreOutputs = scoreOutputs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Map getModelInputs() {
		return modelInputs;
	}
	public void setModelInputs(Map modelInputs) {
		this.modelInputs = modelInputs;
	}
	public Map getScoreOutputs() {
		return scoreOutputs;
	}
	public void setScoreOutputs(Map scoreOutputs) {
		this.scoreOutputs = scoreOutputs;
	}
	public void addInput(String key, JsonObject value){
		if(this.modelInputs == null){
			this.modelInputs = new HashMap<String, JsonObject>();
		
		}
		this.modelInputs.put(key, value);
		
	}
	public void removeInput(String key){	
		this.modelInputs.remove(key);
	}
	public void addOutput(String key, JsonObject value){
		this.scoreOutputs.put(key, value);
		
	}
	public void removeOutput(String key){
		this.scoreOutputs.remove(key);	
	}
}
