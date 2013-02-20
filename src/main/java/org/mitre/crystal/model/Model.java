/**
 * POJO implementation of a model
 */
package org.mitre.crystal.model;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;



/**
 * @author tmlewis
 *
 */
public class Model {
	private String name;
	private String description;
	private Long id;
	
	// input name -> input
	@JsonIgnore
	private Map<String, InputSpecification> inputs;
	
	public Model() {
	}

	public Model(String name, String description, Long id,
			Map<String, InputSpecification> inputs) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.inputs = inputs;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, InputSpecification> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, InputSpecification> inputs) {
		this.inputs = inputs;
	}

	
}
