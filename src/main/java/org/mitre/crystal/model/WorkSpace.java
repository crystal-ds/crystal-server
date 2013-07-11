/**
 * 
 */
package org.mitre.crystal.model;

import java.util.List;

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
	
	private long batchJobID;
	private long modelID;
	private long workSpaceID;
	private List <ModelRunInstance> instances;

	
	public boolean contains(ModelRunInstance o) {
		return instances.contains(o);
	}

	public boolean remove(ModelRunInstance o) {
		return instances.remove(o);
	}
	
	@Column(name="model_id")
	public long getModelID() {
		return modelID;
	}

	public void setModelID(long modelID) {
		this.modelID = modelID;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id")
	public long getWorkSpaceID() {
		return workSpaceID;
	}

	public void setWorkSpaceID(long workSpaceID) {
		this.workSpaceID = workSpaceID;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="batch_id", referencedColumnName="id")
	public List<ModelRunInstance> getInstances() {
		return instances;
	}

	public void setInstances(List<ModelRunInstance> instances) {
		this.instances = instances;
	}

	/**
	 * @return the batchJobID
	 */
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
