/**
 * POJO implementation of a model
 */
package org.mitre.crystal.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;



/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "models")
public abstract class ModelSpecification {
	
	
	
	private String name;
	private String description;
	private Long id;
	@JsonIgnore
	private Map<String, InputNode> inputs;
	
	public ModelSpecification() {
	}

	public ModelSpecification(String name, String description, Long id,
			Map<String, InputNode> inputs) {
		this.name = name;
		this.description = description;
		this.id = id;
		
		this.inputs = inputs;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ElementCollection
	@Column (name = "inputs")
	public Map<String, InputNode> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, InputNode> inputs) {
		this.inputs = inputs;
	}
	
	//TODO do I store this?
	public abstract void run(ModelRunInstance mri);

	
}
