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
public enum ExploratoryModellingInputType {
	//Expected to be used for interacted with UI 
	//TODO Change to reasonable enumerations that Arthur is expecting
	SIMPLE("min", "max", "value"),
	RADIO_BUTTON("checked", "group"),
	CHECKBOX("checked"),	
	RANGESLIDER("min", "max", "low","high");
	
	private Set<String> propertyNames;
	
	ExploratoryModellingInputType(String... props) {
		propertyNames = ImmutableSet.copyOf(props);
	}

	public Set<String> getPropertyNames() {
		return propertyNames;
	}
	
}