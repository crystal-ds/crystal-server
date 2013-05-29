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
	//TODO Change these form UI Types to Input types (Example: RANGE("min","max","stepsize", "upper", "lower"))
	SIMPLE("min", "max", "value"),
	RADIO_BUTTON("checked", "group"),
	CHECKBOX("checked"),	
	RANGESLIDER("min", "max", "low","high"),	
	
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
