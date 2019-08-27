package com.mycore.thebe.process.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.entity.User;

/**
 * Service class to control system's session
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
public interface SessionSecurityService {

	/**
	 * Method to get current {@link User} info based on the session
	 * @param request session
	 * @return current {@link User} info
	 */
	public User getCurrentUser(HttpServletRequest request);
	
	/**
	 * Method to get the {@link ActionUser}
	 * @param request session
	 * @return {@link ActionUser}
	 */
	public ActionUser getActionUser(HttpServletRequest request);

	/**
	 * Method to determine authorize status of {@link ActionUser}
	 * @param actionUser the {@link ActionUser}
	 * @param actionCode user action
	 * @return {@link Boolean} value
	 */
	public boolean isAuthorized(ActionUser actionUser, String actionCode);
	
	/**
	 * Method to clear all set session attributes
	 * @param session current {@link HttpSession}
	 * @throws Exception
	 */
	public void clearSessionAttributes(HttpSession session) throws Exception;
	
	/**
	 * Method to record user activity
	 * @param request {@link HttpServletRequest} data 
	 * @param activity activity name
	 * @throws Exception
	 */
	public void recordLog(HttpServletRequest request, String activity) throws Exception;
	
}
