/**
 * 
 */
package org.mitre.crystal.service;

import java.util.List;

import org.mitre.crystal.model.ModelRunInstance;
import org.mitre.crystal.model.WorkSpace;

/**
 * @author tmlewis
 * Provide CRUD operations for workspaces
 */
public interface WorkSpaceService {
	
	/**
	 * 
	 * @param batchJobID The batchjob that the works space will be created
	 * @return The created workspace
	 */
	public abstract WorkSpace createWorkSpace(Long batchJobID);
	
	/**
	 * 
	 * @param ID The ID of the workspace you want
	 * @return The requested workspace
	 */
	public abstract WorkSpace getWorkSpace(Long ID);
	
	/**
	 * 
	 * @param workSpaceId The workspace ID to be updated
	 * @param mask The mask of Model runs you wish to turn off
	 * @return The updated workspace
	 */
	public abstract WorkSpace updateWorkSpace(Long workSpaceId, List<ModelRunInstance> mask);
	
	/**
	 * 
	 * @param workSpaceId The workspace ID to be updated
	 * @param mask The list of Longs that represent model runs
	 * @return Updated workspace
	 */
	public abstract WorkSpace updateWorkSpaceWithLong(Long workSpaceId, List<Long> mask);
	/**
	 * 
	 * @param ws The workspace to restore
	 * @return The updated workspace
	 */
	public abstract WorkSpace restoreWorkSpace(WorkSpace ws);
	
	
	/**
	 * 
	 * @param ws The workspace to remove
	 */
	public abstract void deleteWorkSpace(WorkSpace ws);

}
