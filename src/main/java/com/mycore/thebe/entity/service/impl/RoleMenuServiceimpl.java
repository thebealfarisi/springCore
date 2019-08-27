package com.mycore.thebe.entity.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.impl.CommonServiceImpl;
import com.mycore.thebe.dao.RoleMenuDao;
import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.RoleMenuService;

/**
 * Implementation of {@link RoleMenuService}
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
@Service("roleMenuService")
public class RoleMenuServiceimpl extends CommonServiceImpl implements RoleMenuService {

	@Autowired
	private RoleMenuDao roleMenuDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public RoleMenu store(RoleMenu entity, ActionUser actionUser) throws Exception {
		RoleMenu result = new RoleMenu();
		String userBy = "";
		try {
			if(actionUser != null) {
				User actUser = actionUser.getUser();
				
				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}
			
			if (entity.getRoleMenuId() == null) {
				entity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
				entity.setCreatedBy(userBy);
				entity.setDeletedStatus(Integer.valueOf(0));
			} else {
				entity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				entity.setModifiedBy(userBy);
			}
			
			result = (RoleMenu) roleMenuDao.storeByEntity(entity);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public RoleMenu delete(RoleMenu entity, ActionUser actionUser) throws Exception {
		RoleMenu result = new RoleMenu();
		String userBy = "";
		try {
			if(actionUser != null) {
				User actUser = actionUser.getUser();
				
				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}
			
			entity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			entity.setModifiedBy(userBy);
			entity.setDeletedStatus(Integer.valueOf(1));
			
			result = (RoleMenu) roleMenuDao.storeByEntity(entity);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
