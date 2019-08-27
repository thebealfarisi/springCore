package com.mycore.thebe.dao;

import java.io.Serializable;

import com.mycore.thebe.common.dao.CommonDao;
import com.mycore.thebe.datamodel.RoleMenuModel;
import com.mycore.thebe.entity.RoleMenu;

/**
 * Data Access Object to communicate with {@link RoleMenu} entity via {@link RoleMenuModel}
 * @author Thebe.Alfarisi
 * @since Nov, 7th 2018
 * @version 1.0
 *
 */
public interface RoleMenuDao extends CommonDao {
	
	/**
	 * Method to create new or update existing {@link RoleMenu} via {@link RoleMenuModel}
	 * @param model new {@link RoleMenu} to be created or updated represented by {@link RoleMenuModel}
	 * @return addition result of {@link RoleMenu} stored in {@link RoleMenuModel}
	 * @throws Exception
	 */
	public RoleMenuModel storeByForm(RoleMenuModel model) throws Exception;
	
	/**
	 * Method to flush existing {@link RoleMenu} via {@link RoleMenuModel}
	 * @param model existing {@link RoleMenu} to be flushed represented by {@link RoleMenuModel}
	 * @return flushed {@link RoleMenu} stored in {@link RoleMenuModel}
	 * @throws Exception
	 */
	public RoleMenuModel flushByForm(RoleMenuModel model) throws Exception;
	
	/**
	 * Method to retrieve {@link RoleMenu} data by it's id via {@link RoleMenuModel}
	 * @param id {@link RoleMenu}'s id to be retrieved represented by {@link RoleMenuModel}
	 * @return retrieved {@link RoleMenu} get by it's id stored in {@link RoleMenuModel}
	 * @throws Exception
	 */
	public RoleMenuModel getByForm(Serializable id) throws Exception;
	
}
