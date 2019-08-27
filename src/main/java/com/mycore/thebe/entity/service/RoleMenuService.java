package com.mycore.thebe.entity.service;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.CommonService;
import com.mycore.thebe.entity.RoleMenu;

/**
 * Service Class to control basic {@link RoleMenu} interaction with database
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public interface RoleMenuService extends CommonService {
	
	/**
	 * Method to create or update {@link RoleMenu} entity data
	 * @param entity {@link RoleMenu} to be created or updated
	 * @param actionUser {@link RoleMenu} who processes
	 * @return created or updated {@link RoleMenu}
	 * @throws Exception
	 */
	public RoleMenu store(RoleMenu entity, ActionUser actionUser) throws Exception;
	
	/**
	 * Method to delete exiting {@link RoleMenu} by updating deletedStatus
	 * @param entity {@link RoleMenu} to be deleted
	 * @param actionUser {@link RoleMenu} who processes
	 * @return deleted {@link RoleMenu}
	 * @throws Exception
	 */
	public RoleMenu delete(RoleMenu entity, ActionUser actionUser) throws Exception;

}
