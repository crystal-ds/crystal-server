/**
 * 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "model_run_inputs")
public class ModelRunInputValues implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4479869752110301867L;
	private Map<String, String> inputs;
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
	@MapKeyColumn (name = "name")
	@Column (name = "property")
	@CollectionTable(name = "model_inputs",
	joinColumns=@JoinColumn(name = "model_input_id"))
	public Map<String, String> getInputs() {
		return inputs;
	}

	public void setInputs(Map<String, String> inputs) {
		this.inputs = inputs;
	}
	
}
