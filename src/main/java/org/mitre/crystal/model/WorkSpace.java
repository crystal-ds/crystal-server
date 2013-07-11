/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author tmlewis
 *
 */
@Entity
@Table(name = "work_space")
@NamedQueries({
	@NamedQuery(name="workspace.findAll", query="SELECT w FROM workspace w")
})
public class WorkSpace {
	
	private Long batchJobID;
	private Long workSpaceID;
	private Map <ModelRunInstance,ScoreRunInstance> instances;

	
	public boolean contains(ModelRunInstance o) {
		return instances.containsKey(o);
	}

	public boolean remove(ModelRunInstance o) {
		return instances.remove(o) != null;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "work_space_id")
	public Long getWorkSpaceID() {
		return workSpaceID;
	}

	public void setWorkSpaceID(long workSpaceID) {
		this.workSpaceID = workSpaceID;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="work_space_id", referencedColumnName="id")
	public Map<ModelRunInstance,ScoreRunInstance> getInstances() {
		return instances;
	}

	public void setInstances(Map<ModelRunInstance,ScoreRunInstance> instances) {
		this.instances = instances;
	}

	/**
	 * @return the batchJobID
	 */
	@Column(name="batch_job_id")
	public long getBatchJobID() {
		return batchJobID;
	}

	/**
	 * @param batchJobID the batchJobID to set
	 */
	public void setBatchJobID(long batchJobID) {
		this.batchJobID = batchJobID;
	}
	
}
