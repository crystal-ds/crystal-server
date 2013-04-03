/**
 * 
 */
package org.mitre.crystal.model;

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

import org.codehaus.jackson.JsonNode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "model_outputs")
public class ModelRunOutputValues {
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
