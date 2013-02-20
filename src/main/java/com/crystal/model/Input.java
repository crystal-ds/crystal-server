/**
 * POJO implementation of an input
 */
package com.crystal.model;
 

import java.util.Map;

import com.google.gson.JsonObject;

/**
 * @author tmlewis
 *
 */
public class Input {
	private String name;

	private String type;
	private Map<String,JsonObject> properties;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Map<String, JsonObject> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, JsonObject> properties) {
		this.properties = properties;
	}
	public void setProperty(String key, JsonObject value){
		this.properties.put(key, value);
	}
	public void removeProperty(String key, JsonObject value){
		this.properties.remove(key);
	}
	public Input(String name, String type, Map<String, JsonObject> properties) {
		super();
		this.name = name;
		this.type = type;
		this.properties = properties;
	}
}
