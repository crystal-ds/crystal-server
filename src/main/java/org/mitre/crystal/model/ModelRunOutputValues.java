/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.JsonNode;

/**
 * @author tmlewis
 *
 */
public class ModelRunOutputValues {
	private Map<String, JsonNode> outputs;
	private long id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ElementCollection
	@Column(name = "model_outputs")
	public Map<String, JsonNode> getOutputs() {
		return outputs;
	}

	public void setOutputs(Map<String, JsonNode> outputs) {
		this.outputs = outputs;
	}
}
