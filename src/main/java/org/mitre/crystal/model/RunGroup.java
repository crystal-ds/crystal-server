/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;
import java.util.Map;

/**
 * @author tmlewis
 *
 */
public class RunGroup {

	private long id;
	private List <ModelRunInstance> runs;
	public RunGroup(long id, List<ModelRunInstance> runs) {
		super();
		this.id = id;
		this.runs = runs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<ModelRunInstance> getRuns() {
		return runs;
	}
	public void setRuns(List<ModelRunInstance> runs) {
		this.runs = runs;
	}
	
	
}
