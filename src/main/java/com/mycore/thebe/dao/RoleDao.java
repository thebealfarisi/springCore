package com.mycore.thebe.dao;

import java.io.Serializable;

import com.mycore.thebe.common.dao.CommonDao;
import com.mycore.thebe.datamodel.RoleModel;
import com.mycore.thebe.entity.Role;

/**
 * Data Access Object to communicate with {@link Role} entity via {@link RoleModel}
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
public interface RoleDao extends CommonDao {

	/**
	 * Method to create or update {@link Role} via {@link RoleModel}
	 * @param model {@link Role} to be created or updated represented by {@link RoleModel}
	 * @return added or updated {@link Role} stored in {@link RoleModel}
	 * @throws Exception
	 */
	public RoleModel storeByForm(RoleModel model) throws Exception;
	
	/**
	 * Method to flush existing {@link Role} via {@link RoleModel}
	 * @param model exiting {@link Role} represented by {@link RoleModel}
	 * @return flushed {@link Role} via {@link RoleModel}
	 * @throws Exception
	 */
	public RoleModel flushByForm(RoleModel model) throws Exception;
	
	/**
	 * Method to retrieved {@link Role} data by it's id and store it to {@link RoleModel}
	 * @param id {@link Role}'s id to be retrieved via {@link RoleModel}
	 * @return retrieved {@link Role} via {@link RoleModel}
	 * @throws Exception
	 */
	public RoleModel getByForm(Serializable id) throws Exception;
	
}
