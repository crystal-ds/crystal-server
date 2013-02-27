/**
 * POJO implementation of an input
 */
package org.mitre.crystal.model;
 

import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.mitre.crystal.model.json.InputSpecificationSerializer;


/**
 * @author tmlewis
 *
 */
@JsonSerialize (using = InputSpecificationSerializer.class)
public class InputSpecification {
	private Long id;

	// unique identifier within a particular model
	private String name;
	// Type of displayed widget <slider, text, etc>
	private DisplayType type;
	
	//name of an input property and it's corresponding value(s)
	private Map<String,JsonNode> properties;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public InputSpecification(){
		
	}
	public InputSpecification(String name, DisplayType type, Map<String, JsonNode> properties) {
		this.name = name;
		this.type = type;
		this.properties = properties;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public DisplayType getType() {
		return type;
	}
	public void setType(DisplayType type) {
		this.type = type;
	}

	public JsonNode getProperty(String key) {
		if (type.getPropertyNames().contains(key)) {
			return properties.get(key);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}
		
	}
	public JsonNode putProperty(String key, JsonNode value) {
		if (type.getPropertyNames().contains(key)) {
			return properties.put(key, value);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}
		
	}
	public JsonNode removeProperty(String key) {
		if (type.getPropertyNames().contains(key)) {
			return properties.remove(key);
		} else {
			throw new IllegalArgumentException("Property Name " + key + " not allowed for type " + type);
		}
		
	}

}
