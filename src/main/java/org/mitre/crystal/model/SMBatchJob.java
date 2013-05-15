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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "score_batch_job")
public class SMBatchJob {

	
	private List<ScoreRunInstance> 	instances;
	private Long smBatchJobid;
	private BatchJobStatus status;
	//private ModelSpecification model;
	private Long scoringModelID;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="batch_id", referencedColumnName="sm_batch_job_id")
	public List<ScoreRunInstance> getInstances() {
		return instances;
	}
	
	
	public void setInstances(List<ScoreRunInstance> instances) {
		this.instances = instances;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "sm_batch_job_id")
	public Long getSmBatchJobid() {
		return smBatchJobid;
	}
	
	public void setSmBatchJobid(Long id) {
		this.smBatchJobid = id;
	}
	
	@Enumerated (EnumType.STRING)
	@Column(name = "status")
	public BatchJobStatus getStatus() {
		return status;
	}
	public void setStatus(BatchJobStatus status) {
		this.status = status;
	}
	
	@Column(name="model_id")
	public Long getScoringModelID() {
		return scoringModelID;
	}
	public void setScoringModelID(Long modelID) {
		this.scoringModelID = modelID;
	}


}
