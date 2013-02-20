/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

/**
 * @author tmlewis
 *
 */
public class RunGroup {

	private long id;
	private List <run> runs;
	public RunGroup(long id, List<run> runs) {
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
	public List<run> getRuns() {
		return runs;
	}
	public void setRuns(List<run> runs) {
		this.runs = runs;
	}
	
	
}
