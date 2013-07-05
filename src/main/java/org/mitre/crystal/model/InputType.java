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
	RANGE("min", "max", "stepsize", "upper", "lower", "start_time", "end_time"), INTEGER(
			"min", "max", "value", "start_time", "end_time"), FLOAT("min",
			"max", "value", "start_time", "end_time"), STRING("regex", "value",
			"start_time", "end_time"), DATE("format", "value", "start_time",
			"end_time"), BOOLEAN("value", "start_time", "end_time");

	private Set<String> propertyNames;

	InputType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}

}
