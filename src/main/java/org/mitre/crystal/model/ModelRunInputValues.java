/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jackson.JsonNode;

/**
 * @author tmlewis
 *
 */
@Entity
public class ModelRunInputValues {
	private Map<String, JsonNode> inputs;
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
	@Column(name = "model_inputs")
	public Map<String, JsonNode> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, JsonNode> inputs) {
		this.inputs = inputs;
	}
	
}
