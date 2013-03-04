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


		private List<ModelRunInstance> 	runs;
		private long id;
		BatchJobStatus status;
		private long modelId;
		
		public BatchJob() {
			
		}

		public List<ModelRunInstance> getRuns() {
			return runs;
		}
		public boolean addRun(ModelRunInstance e) {
			return runs.add(e);
		}
		public ModelRunInstance removeRun(int index) {
			return runs.remove(index);
		}
		public void setRuns(List<ModelRunInstance> list) {
			this.runs = list;
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
