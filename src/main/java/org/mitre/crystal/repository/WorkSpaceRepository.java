/**
 * 
 */
package org.mitre.crystal.repository;

import org.mitre.crystal.model.WorkSpace;

/**
 * @author tmlewis
 *
 */
public interface WorkSpaceRepository {

	/**
	 * @param ws
	 * @return 
	 */
	WorkSpace save(WorkSpace ws);

	/**
	 * @param iD
	 * @return
	 */
	WorkSpace getWorkSpace(Long iD);

	/**
	 * @param ws
	 */
	void delete(WorkSpace ws);

}
