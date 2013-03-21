/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Map;

import org.codehaus.jackson.JsonNode;

/**
 * @author tmlewis
 *
 */
public class ModelRunInputValues {
	private Map<String, JsonNode> inputs;

	public Map<String, JsonNode> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, JsonNode> inputs) {
		this.inputs = inputs;
	}
	
}
