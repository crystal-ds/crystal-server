/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

/**
 * @author tmlewis
 *
 */
public class BatchJob {

		private List<ModelRunInputValues> 	inputs;
		private long id;
		BatchJobStatus status;
		private long modelId;
		
		
		public List<ModelRunInputValues> getInputs() {
			return inputs;
		}
		public boolean add(ModelRunInputValues e) {
			return inputs.add(e);
		}
		public ModelRunInputValues remove(int index) {
			return inputs.remove(index);
		}
		public void setInputs(List<ModelRunInputValues> inputs) {
			this.inputs = inputs;
		}
		public long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public BatchJobStatus getStatus() {
			return status;
		}
		public void setStatus(BatchJobStatus status) {
			this.status = status;
		}
		public long getModelId() {
			return modelId;
		}
		public void setModelId(Long modelId) {
			this.modelId = modelId;
		}

		
}
