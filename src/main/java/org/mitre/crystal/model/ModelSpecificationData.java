/**
 * POJO implementation of a model
 */
package org.mitre.crystal.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "models")
@NamedQueries({
	@NamedQuery(name = "ModelSpecificationData.getAll", query="select m from ModelSpecificationData m")
})
public abstract class ModelSpecificationData {
	
	
	
	private String name;
	private String description;	
	private Long id;
	private List<InputNode> inputs;
	
	public ModelSpecificationData() {
	}

	public ModelSpecificationData(String name, String description, Long id,
			List<InputNode> inputs) {
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
	@CollectionTable(name = "model_inputs", 
	joinColumns = @JoinColumn(name = "inputs_id"))
	@Column(name = "inputs")
	public List<InputNode> getInputs() {
		return inputs;
	}

	public void setInputs(List<InputNode> inputs) {
		this.inputs = inputs;
	}

	
}