/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

/**
 * @author tmlewis
 *
 */
public enum InputType {	
	RANGE("min","max","stepsize", "upper", "lower"),
	INTEGER("min","max","value"),
	FLOAT("min","max","value"),
	STRING("regex", "value"),
	DATE("format", "value"),
	BOOLEAN("value");
	
	
	
	
	private Set<String> propertyNames;
	
	InputType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}
	
}
