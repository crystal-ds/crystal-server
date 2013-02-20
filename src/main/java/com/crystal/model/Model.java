/**
 * POJO implementation of a model
 */
package com.crystal.model;

import java.util.List;
import java.util.Map;



/**
 * @author tmlewis
 *
 */
public class Model {
	private String name;
	private String description;
	private Long id;
	private List<Input> inputs;
	
	
	
	public Model(String name, String description, Long id, List<Input> inputs) {
		super();
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
	public List getInputs() {
		return inputs;
	}
	public void setInputs(List<Input> inputs) {
		this.inputs = inputs;
	}

	
}
