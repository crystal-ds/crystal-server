package org.mitre.crystal.model;
 

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;


/**
 * @author tmlewis
 *
 */
public class InputNode implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Unique ID of the input
	private Long id;
	// unique identifier within a particular model
	private String name;
	// Type of displayed widget <slider, text, etc>
	private InputType type;
	
	//name of an input property and it's corresponding value(s)
	private Map<String, String> properties;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public InputNode(){
		properties = new HashMap<String, String>();
		
	}
	public InputNode(String name, InputType type, Map<String, String> properties) {
		this.name = name;
		this.type = type;
		this.properties = properties;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Enumerated (EnumType.STRING)
	@Column(name = "type")
	public InputType getType() {
		return type;
	}
	
	public void setType(InputType type) {
		this.type = type;
	}

	
	@ElementCollection
	@MapKeyColumn (name = "name")
	@Column (name = "property")
	@CollectionTable(name = "input_node_properties",
	joinColumns=@JoinColumn(name = "input_node_id"))
	public Map<String, String> getProperties() {
		return properties;
	}
	
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	public String getProperty(String key) {
		if (type.getPropertyNames().contains(key)) {
			return properties.get(key);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}
		
	}
	
	public String putProperty(String key, String value) {
		if (type.getPropertyNames().contains(key)) {
			return properties.put(key, value);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}
		
	}
	
	public String removeProperty(String key) {
		if (type.getPropertyNames().contains(key)) {
			return properties.remove(key);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}	
	}
}
