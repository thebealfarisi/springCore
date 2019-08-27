package com.mycore.thebe.dao;

import java.io.Serializable;

import com.mycore.thebe.common.dao.CommonDao;
import com.mycore.thebe.datamodel.ActivityLogModel;
import com.mycore.thebe.entity.ActivityLog;

/**
 * Data Access Object Interface to communicate with {@link ActivityLog} via {@link ActivityLogModel}
 * @author Thebe.Alfarisi
 * @since Nov, 27th 2018
 * @version 1.0
 *
 */
public interface ActivityLogDao extends CommonDao {
	
	/**
	 * Method to create or update {@link ActivityLog} via {@link ActivityLogModel}
	 * @param model {@link ActivityLog} to be created or updated represented by {@link ActivityLogModel}
	 * @return added or updated {@link ActivityLog} stored in {@link ActivityLogModel}
	 * @throws Exception
	 */
	public ActivityLogModel storeByForm(ActivityLogModel model) throws Exception;
	
	/**
	 * Method to flush existing {@link ActivityLog} via {@link ActivityLogModel}
	 * @param model exiting {@link ActivityLog} represented by {@link ActivityLogModel}
	 * @return flushed {@link ActivityLog} via {@link ActivityLogModel}
	 * @throws Exception
	 */
	public ActivityLogModel flushByForm(ActivityLogModel model) throws Exception;
	
	/**
	 * Method to retrieved {@link ActivityLog} data by it's id and store it to {@link ActivityLogModel}
	 * @param id {@link ActivityLog}'s id to be retrieved via {@link ActivityLogModel}
	 * @return retrieved {@link ActivityLog} via {@link ActivityLogModel}
	 * @throws Exception
	 */
	public ActivityLogModel getByForm(Serializable id) throws Exception;
	
}
