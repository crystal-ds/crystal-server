/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

/**
 * @author tmlewis
 *This is the input class for a scoreing model. It is passed to the scoreing 
 *model with a batch job as the rubrick that a ModelRunInstance will be scored against. 
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
