/**
 * 
 */
package org.mitre.crystal.service;

import org.mitre.crystal.model.WorkSpace;

/**
 * @author tmlewis
 *
 */
public interface WorkSpaceService {
	
	public abstract WorkSpace createWorkSpace(Long batchJobID);
	public abstract WorkSpace getWorkSpace(Long ID);
	public abstract WorkSpace updateWorkSpace(WorkSpace ws);
	public abstract WorkSpace restoreWorkSpace(WorkSpace ws);
	public abstract void deleteWorkSpace(WorkSpace ws);

}
