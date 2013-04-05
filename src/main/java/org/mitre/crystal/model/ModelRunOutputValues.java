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
@Table(name = "model_outputs")
public class ModelRunOutputValues  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5576899823061807354L;
	private Map<String, String> outputs;
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
	@CollectionTable(name = "model_outputs",
	joinColumns=@JoinColumn(name = "model_output_id"))
	public Map<String, String> getOutputs() {
		return outputs;
	}

	public void setOutputs(Map<String, String> outputs) {
		this.outputs = outputs;
	}
}
