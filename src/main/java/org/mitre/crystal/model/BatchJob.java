/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "batch_job")
public class BatchJob {

		
	
		private List<ModelRunInstance> 	instances;
		private Long id;
		private BatchJobStatus status;
		private ModelSpecification model;
		
		public BatchJob() {
			
		}

		@OneToMany(mappedBy = "batch_id")
		public List<ModelRunInstance> getInstances() {
			return instances;
		}


		public void setInstances(List<ModelRunInstance> instances) {
			this.instances = instances;
		}


		public void setId(Long id) {
			this.id = id;
		}


		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column (name = "id")
		public Long getId() {
			return id;
		}

		@Enumerated (EnumType.STRING)
		@Column(name = "status")
		public BatchJobStatus getStatus() {
			return status;
		}
		public void setStatus(BatchJobStatus status) {
			this.status = status;
		}

		@ManyToOne 
		@JoinColumn (name = "model_id")
		public ModelSpecification getModel() {
			return model;
		}

		/**
		 * @param model
		 */
		public void setModel(ModelSpecification model) {
			this.model = model;
			
		}

		
}
