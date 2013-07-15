/**
 * 
 */
package org.mitre.crystal.model;

import java.util.HashMap;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * @author tmlewis
 *
 */
@Entity
@Table(name = "work_space")
public class WorkSpace{
	
	private Long workSpaceID;
	private List<ModelRunInstance> offMask;
	private SMBatchJob smbj;
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
	
	@Transient
	public Map<ModelRunInstance,ScoreRunInstance> getWorkSpaceMap(){
//		Map<ModelRunInstance, ScoreRunInstance> m = new HashMap<ModelRunInstance, ScoreRunInstance>();
//		
//		Predicate<ModelRunInstance> isMasked = new Predicate<ModelRunInstance>(){
//			@Override
//			public boolean apply(ModelRunInstance m){
//				return !offMask.contains(m);	
//			}
//		};
//		
//		Iterable<ModelRunInstance> i = Iterables.filter(batchJob.getInstances(), isMasked);
//		for (ModelRunInstance modelRunInstance : i) {
//			for (ScoreRunInstance runInstance : smbj.getInstances()) {
//				if(runInstance.getMriJobInstanceID() == modelRunInstance.getId()){
//					m.put(modelRunInstance, runInstance);
//				}
//					
//			}
//		}
//		return m;
		
		
		Map<ModelRunInstance, ScoreRunInstance> m = new HashMap<ModelRunInstance, ScoreRunInstance>();
		List<ModelRunInstance> l = batchJob.getInstances();
		for (ModelRunInstance modelRunInstance : l) {
			List<ScoreRunInstance> l2 = smbj.getInstances();
			for (ScoreRunInstance scoreRunInstance : l2) {
				if(modelRunInstance.getId() == scoreRunInstance.getMriJobInstanceID())
					if(!offMask.contains(modelRunInstance)){
						m.put(modelRunInstance, scoreRunInstance);
					}
			}
		}
		return m;
	}
}
