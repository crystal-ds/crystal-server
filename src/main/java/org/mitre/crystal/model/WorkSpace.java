package org.mitre.crystal.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author tmlewis
 *The workspace was suppose to a quick work around to make batchjobs appear "editible"
 *They work by adjusting a mask which is the list of runs that are NOT in the workspace
 */
@Entity
@Table(name = "work_space")
public class WorkSpace{

	//Unique generated ID for the workspace
	private Long workSpaceID;
	//The mask of ModelRuns that are not in the Workspace
	private List<ModelRunInstance> offMask;
	//The scores assocated with the batchjob
	private SMBatchJob smbj;
	//The batchjob assocated with this workspace
	private BatchJob batchJob;



	@ManyToOne
	@JoinColumn(name = "sm_batch_job_id", referencedColumnName = "sm_batch_job_id")
	public SMBatchJob getSmbj() {
		return smbj;
	}

	public void setSmbj(SMBatchJob smbj) {
		this.smbj = smbj;
	}

	@ManyToOne
	@JoinColumn(name = "batch_job_id", referencedColumnName = "id")
	public BatchJob getBatchJob() {
		return batchJob;
	}

	public void setBatchJob(BatchJob batchJob) {
		this.batchJob = batchJob;
	}


	@OneToMany
	@JoinTable(name="masks",
	joinColumns={@JoinColumn(name="work_space_id")},
	inverseJoinColumns={@JoinColumn(name="model_run_id")}
			)
	public List<ModelRunInstance> getOffMask() {
		return offMask;
	}


	public void setOffMask(List<ModelRunInstance> offMask) {
		this.offMask = offMask;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "work_space_id")
	public Long getWorkSpaceID() {
		return workSpaceID;
	}

	public void setWorkSpaceID(Long workSpaceID) {
		this.workSpaceID = workSpaceID;
	}

	/**
	 * 
	 * @return A mapping of model Runs and their corosponding scoreing runs. This map has filtered using the 
	 * workspace's mask. 
	 */
	@Transient
	public Map<ModelRunInstance,ScoreRunInstance> getWorkSpaceMap(){
		//Map we'll eventualy return
		final Map<ModelRunInstance, ScoreRunInstance> m = new HashMap<ModelRunInstance, ScoreRunInstance>();
		//The full list of batch jobs
		final List<ModelRunInstance>  l = batchJob.getInstances();
		//Run through the list and pair it up with the score
		for (final ModelRunInstance modelRunInstance : l) {
			final List<ScoreRunInstance> l2 = smbj.getInstances();
			for (final ScoreRunInstance scoreRunInstance : l2) {
				if(modelRunInstance.getId() == scoreRunInstance.getMriJobInstanceID()) {
					//filter it
					if(!offMask.contains(modelRunInstance)){
						m.put(modelRunInstance, scoreRunInstance);
					}
				}
			}
		}
		return m;
	}
}
