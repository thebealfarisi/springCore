package com.mycore.thebe.process.service.impl;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.web.HttpUtils;
import com.mycore.thebe.entity.ActivityLog;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.ActivityLogService;
import com.mycore.thebe.process.service.SessionSecurityService;

/**
 * Implementation of {@link SessionSecurityService}
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
@Service("sessionSecurityService")
public class SessionSecurityServiceImpl implements SessionSecurityService {
	
	@Autowired
	private ActivityLogService activityLogService;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public User getCurrentUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		User user = null;
		
		if (session != null) {
			user = (User) session.getAttribute("userData");
		}
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public ActionUser getActionUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		
		ActionUser actionUser = null;
		
		if (session != null) {
			User user = (User) session.getAttribute("sessionUser");
			
			actionUser = new ActionUser();
			actionUser.setUser(user);
		}
		
		return actionUser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public boolean isAuthorized(ActionUser actionUser, String actionCode) {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			if (actionUser != null) {
//				User user = actionUser.getUser();
				
				//CHECKING ROLE
				
				//IF ROLE EXIST RETURN TRUE
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
	@Transactional
	public void clearSessionAttributes(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		session.removeAttribute("sessionUser");
		session.removeAttribute("sessionIp");
		session.removeAttribute("sessionId");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public void recordLog(HttpServletRequest request, String activity) throws Exception {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			
			if (session != null) {
				String ipAddress = (String) session.getAttribute("sessionIp");
				String sessionId = (String) session.getAttribute("sessionId");
				User sessionUser = (User) session.getAttribute("sessionUser");
				
				StringBuilder urlPath = new StringBuilder(HttpUtils.getUrl(request).toString());
				if (HttpUtils.getQueryPath(request) != null) {
					urlPath = urlPath.append("?").append(HttpUtils.getQueryPath(request).toString());
				}
				
				ActivityLog activityLog = new ActivityLog();
				
				activityLog.setActionProcess(activity);
				activityLog.setActionTime(new Timestamp(System.currentTimeMillis()));
				activityLog.setActionUrl(urlPath.toString());
				activityLog.setIpAddress(ipAddress);
				activityLog.setSessionId(sessionId);
				activityLog.setUserId(sessionUser);
				activityLog.setActionUser(sessionUser.getUsername());
				
				activityLogService.store(activityLog);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
