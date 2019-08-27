package com.mycore.thebe.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycore.thebe.common.dao.impl.CommonDaoImpl;
import com.mycore.thebe.dao.ActivityLogDao;
import com.mycore.thebe.datamodel.ActivityLogModel;
import com.mycore.thebe.entity.ActivityLog;

/**
 * Implementation of {@link ActivityLogDao}
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
@Repository("activityLogDao")
public class ActivityLogDaoImpl extends CommonDaoImpl implements ActivityLogDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private ActivityLog activityLog;
	private ActivityLogModel activityLogModel;

	/**
	 * @inheritDoc
	 */
	public ActivityLogModel storeByForm(ActivityLogModel model) throws Exception {
		activityLogModel = null;
		try {
			activityLog = new ActivityLog(model);
			this.sessionFactory.getCurrentSession().saveOrUpdate(activityLog);
			activityLogModel = (ActivityLogModel) activityLog.toModel(ActivityLogModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return activityLogModel;
	}

	/**
	 * @inheritDoc
	 */
	public ActivityLogModel flushByForm(ActivityLogModel model) throws Exception {
		activityLogModel = null;
		try {
			activityLog = new ActivityLog(model);
			this.sessionFactory.getCurrentSession().delete(activityLog);
			activityLogModel = (ActivityLogModel) activityLog.toModel(ActivityLogModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return activityLogModel;
	}

	/**
	 * @inheritDoc
	 */
	public ActivityLogModel getByForm(Serializable id) throws Exception {
		activityLogModel = null;
		try {
			activityLog = (ActivityLog) this.sessionFactory.getCurrentSession().get(ActivityLog.class, id);
			activityLogModel = (ActivityLogModel) activityLog.toModel(ActivityLogModel.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return activityLogModel;
	}

}
