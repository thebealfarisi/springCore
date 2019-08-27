package com.mycore.thebe.dao;

import java.io.Serializable;

import com.mycore.thebe.common.dao.CommonDao;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;

/**
 * Data Access Object Interface to communicate with {@link User} entity and by form via {@link UserModel}
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
public interface UserDao extends CommonDao {

	/**
	 * Method to create new {@link User} or update existing {@link User} via {@link UserModel} by Form Process
	 * @param model {@link UserModel} to be create or update then converted to {@link User}
	 * @return added or updated {@link User} stored in {@link UserModel}
	 * @throws Exception
	 */
	public UserModel storeByForm(UserModel model) throws Exception;
	
	/**
	 * Method to flush existing {@link User} via {@link UserModel} by Form Process
	 * @param model existing {@link User} represented by {@link UserModel} 
	 * @return flushed {@link User} via {@link UserModel}
	 * @throws Exception
	 */
	public UserModel flushByForm(UserModel model) throws Exception;
	
	/**
	 * Method to retrieve {@link User} data by it's id and store it to {@link UserModel} by Form Process
	 * @param id {@link User}'s id to be retrieved via {@link UserModel}
	 * @return retrieved {@link User} get by it's id and store it to {@link UserModel}
	 * @throws Exception
	 */
	public UserModel getByForm(Serializable id) throws Exception;
	
}
