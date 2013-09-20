/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 
 * @author tmlewis
 *A BatchJob is the storage mechanism for keeping together ModelRunInstances of a specific 
 *set of model runs. Batchjobs are currently stored in a database.
 */
@Entity
@Table(name = "batch_job")
@NamedQueries({
	@NamedQuery(name="BatchJob.findAll", query="SELECT b FROM BatchJob b")
})
public class BatchJob{

		//The list of instances that we are storing
		private List<ModelRunInstance> 	instances;
		//A unique ID of the batchjob that can be used to retrieve data
		private Long id;
		//The current status of a batchjob
		private BatchJobStatus status;
		//private ModelSpecification model;
		private Long modelID;
		
		public BatchJob() {
			
		}


		/**
		 * 
		 * @return The list of ModelRunInstances that the batchjob represents
		 */
		@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
		@JoinColumn(name="batch_id", referencedColumnName="id")
		public List<ModelRunInstance> getInstances() {
			return instances;
		}


		/**
		 * 
		 * @param instances The list of ModelRunInstances that the batchjob will managing
		 */
		public void setInstances(List<ModelRunInstance> instances) {
			this.instances = instances;		}


		public void setId(Long id) {
			this.id = id;
		}


		/**
		 * Unique ID of the batchjob
		 * @return
		 */
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


		/**
		 * @return the modelID
		 */
		@Column(name="model_id")
		public Long getModelID() {
			return modelID;
		}

		/**
		 * @param modelID the modelID to set
		 */
		public void setModelID(Long modelID) {
			this.modelID = modelID;
		}

		
}
