package com.mycore.thebe.common.service.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.common.dao.CommonDao;
import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.hibernate.HibernateAssistant;
import com.mycore.thebe.common.service.CommonService;
import com.mycore.thebe.entity.User;

/**
 * Implementation of {@link CommonService}
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0 
 *
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object store(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.storeByEntity(entity);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object store(Object entity, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		String userBy = "";
		
		if (actionUser != null) {
			User actUser = actionUser.getUser();

			if (actUser != null) {
				userBy = actUser.getFirstName() + " " + actUser.getLastName();
			}
		}
		
		String pKey = entity.getClass().getSimpleName().toLowerCase() + "Id";
		
		boolean isNew = true;
		
		Field[] entityFields01 = entity.getClass().getDeclaredFields();
		Field[] entityFields02 = entity.getClass().getSuperclass().getDeclaredFields();
		Field[] entityFields = ArrayUtils.addAll(entityFields01, entityFields02);
		
		if (entityFields.length > 0) {
			for(Field entityField : entityFields) {
				entityField.setAccessible(true);
				if (!entityField.getName().equalsIgnoreCase("serialVersionUID")) {
					if (entityField.getName().equalsIgnoreCase(pKey)) {
						if (entityField.get(entity) != null) {
							isNew = false;
						}
					}
				}
			}
			
			for(Field entityField : entityFields) {
				entityField.setAccessible(true);
				if (!entityField.getName().equalsIgnoreCase("serialVersionUID")) {
					if (isNew) {
						if (entityField.getName().equalsIgnoreCase("createdTime")) {
							entityField.set(entity, new Timestamp(System.currentTimeMillis()));
						} 
						if (entityField.getName().equalsIgnoreCase("createdBy")) {
							entityField.set(entity, userBy);
						}
						if (entityField.getName().equalsIgnoreCase("deletedStatus")) {
							entityField.set(entity, Integer.valueOf(0));
						}
					} else {
						if (entityField.getName().equalsIgnoreCase("modifiedTime")) {
							entityField.set(entity, new Timestamp(System.currentTimeMillis()));
						} 
						if (entityField.getName().equalsIgnoreCase("modifiedBy")) {
							entityField.set(entity, userBy);
						}
					}
				}
			}
		}
		
		return commonDao.storeByEntity(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object delete(Object entity) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.flushByEntity(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object getData(Serializable id, Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		return commonDao.getByEntity(id, clazz);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> getAllData(Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			result = cr.list();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02) throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			if (StringUtils.isNotBlank(filter)) {
				Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
				
				filter = (StringUtils.trim(filter)).toUpperCase();
				if (filter.equalsIgnoreCase("LIKE")) {
					HibernateAssistant.setLikes(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("EQUALS")) {
					HibernateAssistant.setEquals(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("BETWEEN")) {
					HibernateAssistant.setBetween(columns, params01, params02, cr);
				} else {
					return null;
				}
				
				result = cr.list();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01,
			Object[] params02, boolean isAsc, String[] sortColumns)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			if (StringUtils.isNotBlank(filter)) {
				Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
				
				filter = (StringUtils.trim(filter)).toUpperCase();
				if (filter.equalsIgnoreCase("LIKE")) {
					HibernateAssistant.setLikes(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("EQUALS")) {
					HibernateAssistant.setEquals(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("BETWEEN")) {
					HibernateAssistant.setBetween(columns, params01, params02, cr);
				} else {
					return null;
				}
				
				HibernateAssistant.setOrdering(isAsc, sortColumns, cr);
				
				result = cr.list();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01,
			Object[] params02, int initialRow, int maxData)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			if (StringUtils.isNotBlank(filter)) {
				Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
				
				filter = (StringUtils.trim(filter)).toUpperCase();
				if (filter.equalsIgnoreCase("LIKE")) {
					HibernateAssistant.setLikes(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("EQUALS")) {
					HibernateAssistant.setEquals(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("BETWEEN")) {
					HibernateAssistant.setBetween(columns, params01, params02, cr);
				} else {
					return null;
				}
				
				HibernateAssistant.setLimit(initialRow, maxData, cr);
				
				result = cr.list();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01,
			Object[] params02, boolean isAsc, String[] sortColumns, int initialRow, int maxData)
			throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			if (StringUtils.isNotBlank(filter)) {
				Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
				
				filter = (StringUtils.trim(filter)).toUpperCase();
				if (filter.equalsIgnoreCase("LIKE")) {
					HibernateAssistant.setLikes(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("EQUALS")) {
					HibernateAssistant.setEquals(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("BETWEEN")) {
					HibernateAssistant.setBetween(columns, params01, params02, cr);
				} else {
					return null;
				}

				HibernateAssistant.setOrdering(isAsc, sortColumns, cr);
				HibernateAssistant.setLimit(initialRow, maxData, cr);
				
				result = cr.list();
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			
			HibernateAssistant.setLikes(likeCols, likePars, cr);
			HibernateAssistant.setEquals(eqCols, eqPars, cr);
			HibernateAssistant.setBetween(betweenCols, between01Pars, between02Pars, cr);
			
			result = cr.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, boolean isAsc, String[] sortColumns) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			
			HibernateAssistant.setLikes(likeCols, likePars, cr);
			HibernateAssistant.setEquals(eqCols, eqPars, cr);
			HibernateAssistant.setBetween(betweenCols, between01Pars, between02Pars, cr);
			HibernateAssistant.setOrdering(isAsc, sortColumns, cr);
			
			result = cr.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, int initialRow, int maxData) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			
			HibernateAssistant.setLikes(likeCols, likePars, cr);
			HibernateAssistant.setEquals(eqCols, eqPars, cr);
			HibernateAssistant.setBetween(betweenCols, between01Pars, between02Pars, cr);
			HibernateAssistant.setLimit(initialRow, maxData, cr);
			
			result = cr.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, boolean isAsc, String[] sortColumns, int initialRow, int maxData) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			
			HibernateAssistant.setLikes(likeCols, likePars, cr);
			HibernateAssistant.setEquals(eqCols, eqPars, cr);
			HibernateAssistant.setBetween(betweenCols, between01Pars, between02Pars, cr);
			HibernateAssistant.setOrdering(isAsc, sortColumns, cr);
			HibernateAssistant.setLimit(initialRow, maxData, cr);
			
			result = cr.list();
			
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
	public int totalData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02)
			throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			if (StringUtils.isNotBlank(filter)) {
				Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
				
				filter = (StringUtils.trim(filter)).toUpperCase();
				if (filter.equalsIgnoreCase("LIKE")) {
					HibernateAssistant.setLikes(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("EQUALS")) {
					HibernateAssistant.setEquals(columns, params01, cr);
				} else if (filter.equalsIgnoreCase("BETWEEN")) {
					HibernateAssistant.setBetween(columns, params01, params02, cr);
				} else {
					return 0;
				}
				
				result = HibernateAssistant.getTotalData(cr);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional
	public int totalDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars) throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			
			HibernateAssistant.setLikes(likeCols, likePars, cr);
			HibernateAssistant.setEquals(eqCols, eqPars, cr);
			HibernateAssistant.setBetween(betweenCols, between01Pars, between02Pars, cr);
			
			result = HibernateAssistant.getTotalData(cr);
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
	public Object getUnique(Class<?> clazz, String[] columns, Object[] params) throws Exception {
		// TODO Auto-generated method stub
		Object result = new Object();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			HibernateAssistant.setEquals(columns, params, cr);
			
			result = cr.uniqueResult();
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Object getSingleData(Class<?> clazz, String[] columns, Object[] params) throws Exception {
		// TODO Auto-generated method stub
		Object result = null;
		List<Object> tempResult = new ArrayList<Object>();
		
		try {
			Criteria cr = sessionFactory.getCurrentSession().createCriteria(clazz);
			HibernateAssistant.setEquals(columns, params, cr);
			
			tempResult = cr.list();
			
			if (tempResult != null && tempResult.size() > 0) {
				result = tempResult.get(0);
			}
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Object> getEntityBySP(String procedure, Object[] inputParameter, Class<?> clazz) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			
			String callProcedure = "CALL " + procedure;
			
			if (inputParameter != null && inputParameter.length > 1) {
				for (int stepData = 0; stepData < inputParameter.length; stepData++) {
					if (stepData == 0) {	
						callProcedure = "(:" + stepData;
					} else {
						if ((stepData + 1) == inputParameter.length) {
							callProcedure = " ,:" + stepData + ")";
						} else {
							callProcedure = " ,:" + stepData;
						}
					}
				}
			} else {
				callProcedure += "()";
			}
			
			Query query = sessionFactory.getCurrentSession().createSQLQuery(callProcedure).addEntity(clazz);
			
			if (inputParameter.length > 1) {
				for (int stepData = 0; stepData < inputParameter.length; stepData++) {
					query.setParameter("" + stepData, inputParameter[stepData]);
				}
			}
			
			result = query.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
