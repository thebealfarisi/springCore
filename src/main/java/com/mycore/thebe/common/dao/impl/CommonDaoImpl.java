package com.mycore.thebe.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycore.thebe.common.dao.CommonDao;

/**
 * Implementation of {@link CommonDao}
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
@Repository("commonDao")
public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	public Object storeByEntity(Object entity) throws Exception {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object flushByEntity(Object entity) throws Exception {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().delete(entity);
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getByEntity(Serializable id, Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		return this.sessionFactory.getCurrentSession().get(clazz, id);
	}
	
}
