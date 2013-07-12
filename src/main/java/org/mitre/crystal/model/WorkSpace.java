/**
 * 
 */
package org.mitre.crystal.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
	
	private Long workSpaceID;
	private List<Long> offMask;
	private BatchJob bj;
	private SMBatchJob smbj;

	
	
	public SMBatchJob getSmbj() {
		return smbj;
	}

	public void setSmbj(SMBatchJob smbj) {
		this.smbj = smbj;
	}

	
	
	public boolean contains(Object o) {
		return offMask.contains(o);
	}

	public boolean addAll(Collection<? extends Long> c) {
		return offMask.addAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return offMask.retainAll(c);
	}

	public void add(int index, Long element) {
		offMask.add(index, element);
	}

	public Long remove(int index) {
		return offMask.remove(index);
	}

	private BatchJob batchJob;

	
	
	
	public BatchJob getBatchJob() {
		return batchJob;
	}

	public void setBatchJob(BatchJob batchJob) {
		this.batchJob = batchJob;
	}

	@ElementCollection
	@CollectionTable(name="mask")
	public List<Long> getOffMask() {
		return offMask;
	}

	
	public void setOffMask(List<Long> offMask) {
		this.offMask = offMask;
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
	
}
