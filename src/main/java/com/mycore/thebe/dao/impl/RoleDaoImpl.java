package com.mycore.thebe.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycore.thebe.common.dao.impl.CommonDaoImpl;
import com.mycore.thebe.dao.RoleDao;
import com.mycore.thebe.datamodel.RoleModel;
import com.mycore.thebe.entity.Role;

/**
 * Implementation of {@link RoleDao}
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends CommonDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Role role;
	private RoleModel roleModel;

	/**
	 * {@inheritDoc}
	 */
	public RoleModel storeByForm(RoleModel model) throws Exception {
		roleModel = null;
		try {
			role = new Role(model);
			this.sessionFactory.getCurrentSession().saveOrUpdate(role);
			roleModel = (RoleModel) role.toModel(RoleModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public RoleModel flushByForm(RoleModel model) throws Exception {
		roleModel = null;
		try {
			role = new Role(model);
			this.sessionFactory.getCurrentSession().delete(role);
			roleModel = (RoleModel) role.toModel(RoleModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleModel;
	}

	/**
	 * {@inheritDoc}
	 */
	public RoleModel getByForm(Serializable id) throws Exception {
		roleModel = null;
		try {
			role = (Role) this.sessionFactory.getCurrentSession().get(Role.class, id);
			roleModel = (RoleModel) role.toModel(RoleModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return roleModel;
	}

}
