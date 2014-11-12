/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * @author tmlewis
 *This is the current set of types of input the front end and handle
 *Each field in the InputType has a set of properties that can corrspond to that instance
 *
 */
public enum InputType {	
	RANGE("min","max","stepsize", "upper", "lower"),
	INTEGER("min","max","value"),
	FLOAT("min","max","value"),
	STRING("regex", "value"),
	DATE("format", "value"),
	BOOLEAN("value"),
	EXACT("value");
	
	
	
	
	private Set<String> propertyNames;
	
	InputType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}
	
}
