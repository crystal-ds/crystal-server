/**
 * 
 */
package org.mitre.crystal.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author tmlewis
 *
 */
public class ScoringModelInput {

	private Long batchJobId;
	private List<InputNode> inputs;
	public Long getBatchJobId() {
		return batchJobId;
	}
	public void setBatchJobId(Long batchJobId) {
		this.batchJobId = batchJobId;
	}
	public List<InputNode> getInputs() {
		return inputs;
	}
	public void setInputs(List<InputNode> inputs) {
		this.inputs = inputs;
	}
	
}
