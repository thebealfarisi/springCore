package com.mycore.thebe.datamodel;

import java.io.Serializable;

import com.mycore.thebe.entity.ActivityLog;

/**
 * Class to handle {@link ActivityLog} data processing
 * @author Thebe.Alfarisi
 * @since Nov, 26th 2018
 * @version 1.0
 *
 */
public class ActivityLogModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String activityLogId;
	private String actionProcess;
	private String actionUrl;
	private String actionTime;
	private String userName;
	private String sessionId;
	private String ipAddress;
	private String userId;
	
	//CHECK IS NEW
	private boolean isNew = false;
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	//
	
	public String getActivityLogId() {
		return activityLogId;
	}
	public void setActivityLogId(String activityLogId) {
		this.activityLogId = activityLogId;
	}
	public String getActionProcess() {
		return actionProcess;
	}
	public void setActionProcess(String actionProcess) {
		this.actionProcess = actionProcess;
	}
	public String getActionUrl() {
		return actionUrl;
	}
	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	public String getActionTime() {
		return actionTime;
	}
	public void setActionTime(String actionTime) {
		this.actionTime = actionTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
