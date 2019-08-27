package com.mycore.thebe.entity.service;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.CommonService;
import com.mycore.thebe.entity.Role;

/**
 * Service Class to control basic {@link Role} interaction with database
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public interface RoleService extends CommonService {
	
	/**
	 * Method to create or update {@link Role} entity data
	 * @param entity {@link Role} to be created or updated
	 * @param actionUser {@link Role} who processes
	 * @return created or updated {@link Role}
	 * @throws Exception
	 */
	public Role store(Role entity, ActionUser actionUser) throws Exception;
	
	/**
	 * Method to delete exiting {@link Role} by updating deletedStatus
	 * @param entity {@link Role} to be deleted
	 * @param actionUser {@link Role} who processes
	 * @return deleted {@link Role}
	 * @throws Exception
	 */
	public Role delete(Role entity, ActionUser actionUser) throws Exception;

}
