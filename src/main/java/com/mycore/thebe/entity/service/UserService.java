package com.mycore.thebe.entity.service;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.CommonService;
import com.mycore.thebe.entity.User;

/**
 * Service Class to control basic {@link User} interaction with database
 * @author Thebe.Alfarisi
 * @since Nov, 30th 2018
 * @version 1.0
 *
 */
public interface UserService extends CommonService {

	/**
	 * Method to create or update {@link User} entity data
	 * @param entity {@link User} to be created or updated
	 * @param actionUser {@link User} who processes
	 * @return created or updated {@link User}
	 * @throws Exception
	 */
	public User store(User entity, ActionUser actionUser) throws Exception;
	
	/**
	 * Method to delete exiting {@link User} by updating deletedStatus
	 * @param entity {@link User} to be deleted
	 * @param actionUser {@link User} who processes
	 * @return deleted {@link User}
	 * @throws Exception
	 */
	public User delete(User entity, ActionUser actionUser) throws Exception;

}
