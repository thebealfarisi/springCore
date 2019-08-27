package com.mycore.thebe.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycore.thebe.common.dao.impl.CommonDaoImpl;
import com.mycore.thebe.dao.UserDao;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;

/**
 * Implementation of {@link UserDao}
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
@Repository("userDao")
public class UserDaoImpl extends CommonDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private User user;
	private UserModel userModel;

	/**
	 * {@inheritDoc}
	 */
	public UserModel storeByForm(UserModel model) throws Exception {
		userModel = null;
		try {
			user = new User(model);
			this.sessionFactory.getCurrentSession().saveOrUpdate(user);
			userModel = (UserModel) user.toModel(UserModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return userModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public UserModel flushByForm(UserModel model) throws Exception {
		userModel = null;
		try {
			user = new User(model);
			this.sessionFactory.getCurrentSession().delete(user);
			userModel = (UserModel) user.toModel(UserModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return userModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public UserModel getByForm(Serializable id) throws Exception {
		userModel = null;
		try {
			user = (User) this.sessionFactory.getCurrentSession().get(User.class, id);
			userModel = (UserModel) user.toModel(UserModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return userModel;
	}
	
}
