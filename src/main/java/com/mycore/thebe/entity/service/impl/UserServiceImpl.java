/**
 * 
 */
package com.mycore.thebe.entity.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.service.impl.CommonServiceImpl;
import com.mycore.thebe.dao.UserDao;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.UserService;

/**
 * Implementation of {@link UserService}
 * @author Thebe.Alfarisi
 * @since Nov, 30th 2018
 * @version 1.0
 *
 */
@Service("userService")
public class UserServiceImpl extends CommonServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
//	@Autowired
//	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public User store(User entity, ActionUser actionUser) throws Exception {
		User result = new User();
		String userBy = "";
		try {
			if(actionUser != null) {
				User actUser = actionUser.getUser();
				
				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}
			
			if (entity.getUserId() == null) {
				entity.setCreatedTime(new Timestamp(System.currentTimeMillis()));
				entity.setCreatedBy(userBy);
				entity.setDeletedStatus(Integer.valueOf(0));
			} else {
				entity.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				entity.setModifiedBy(userBy);
			}
			
			result = (User) userDao.storeByEntity(entity);
			
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
	public User delete(User entity, ActionUser actionUser) throws Exception {
		User result = new User();
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
			
			result = (User) userDao.storeByEntity(entity);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
}
