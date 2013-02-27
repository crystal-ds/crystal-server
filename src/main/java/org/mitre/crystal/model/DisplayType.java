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

	SIMPLESLIDER("min", "max", "value"),
	RADIO_BUTTON("checked", "group"),
	CHECKBOX("checked"),	
	RANGESLIDER("min", "max", "low","high");
	
	private Set<String> propertyNames;
	
	DisplayType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}
	
}
