package com.mycore.thebe.entity.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.impl.CommonServiceImpl;
import com.mycore.thebe.dao.RoleDao;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.RoleService;

/**
 * Implementation of {@link RoleService}
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
@Service("roleService")
public class RoleServiceImpl extends CommonServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Role store(Role entity, ActionUser actionUser) throws Exception {
		Role result = new Role();
		String userBy = "";
		try {
			if(actionUser != null) {
				User actUser = actionUser.getUser();
				
				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}
			
			if (entity.getRoleId() == null) {
				entity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
				entity.setCreatedBy(userBy);
				entity.setDeletedStatus(Integer.valueOf(0));
			} else {
				entity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				entity.setModifiedBy(userBy);
			}
			
			result = (Role) roleDao.storeByEntity(entity);
			
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
	public Role delete(Role entity, ActionUser actionUser) throws Exception {
		Role result = new Role();
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
			
			result = (Role) roleDao.storeByEntity(entity);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}
