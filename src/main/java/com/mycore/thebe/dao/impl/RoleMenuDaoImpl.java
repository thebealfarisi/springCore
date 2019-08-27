package com.mycore.thebe.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycore.thebe.common.dao.impl.CommonDaoImpl;
import com.mycore.thebe.dao.RoleMenuDao;
import com.mycore.thebe.datamodel.RoleMenuModel;
import com.mycore.thebe.entity.RoleMenu;

/**
 * Implementation of {@link RoleMenuDao}
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends CommonDaoImpl implements RoleMenuDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private RoleMenu roleMenu;
	private RoleMenuModel roleMenuModel;
	
	/**
	 * {@inheritDoc}
	 */
	public RoleMenuModel storeByForm(RoleMenuModel model) throws Exception {
		// TODO Auto-generated method stub
		roleMenuModel = null;
		try {
			roleMenu = new RoleMenu(model);
			this.sessionFactory.getCurrentSession().saveOrUpdate(roleMenu);
			roleMenuModel = (RoleMenuModel) roleMenu.toModel(RoleMenuModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleMenuModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public RoleMenuModel flushByForm(RoleMenuModel model) throws Exception {
		// TODO Auto-generated method stub
		roleMenuModel = null;
		try {
			roleMenu = new RoleMenu(model);
			this.sessionFactory.getCurrentSession().delete(roleMenu);
			roleMenuModel = (RoleMenuModel) roleMenu.toModel(RoleMenuModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleMenuModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public RoleMenuModel getByForm(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		roleMenuModel = null;
		try {
			roleMenu = (RoleMenu) this.sessionFactory.getCurrentSession().get(RoleMenu.class, id);
			roleMenuModel = (RoleMenuModel) roleMenu.toModel(RoleMenuModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleMenuModel;
	}

}
