/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

/**
 * @author tmlewis
 *
 */
public enum DisplayType {

	SIMPLESLIDER("min", "max", "default"),
	RADIO_BUTTON("checked", "group"),
	CHECKBOX("checked");	
	
	private Set<String> propertyNames;
	
	DisplayType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}
	
}
