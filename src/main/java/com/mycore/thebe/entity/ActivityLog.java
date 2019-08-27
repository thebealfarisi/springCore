package com.mycore.thebe.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.beans.BeanUtils;

import com.mycore.thebe.datamodel.ActivityLogModel;

/**
 * Activity log entity to store user activity and associate with table activity_log
 * @author Thebe.Alfarisi
 * @since Nov, 26th 2018
 * @version 1.0
 *
 */
@Entity
@Table(name="activity_log")
public class ActivityLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="activity_log_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer activityLogId;
	
	@Column(name="action_process")
	private String actionProcess;
	
	@Column(name="action_url")
	private String actionUrl;
	
	@Column(name="action_time")
	private Timestamp actionTime;
	
	@Column(name="action_user")
	private String actionUser;
	
	@Column(name="session_id")
	private String sessionId;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	//FOREIGN KEY
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@Fetch(FetchMode.JOIN)
	private User userId;
	
	public ActivityLog() {
		
	}
	
	public ActivityLog(ActivityLogModel model) {
		this.toEntity(model);
	}

	public Integer getActivityLogId() {
		return activityLogId;
	}

	public void setActivityLogId(Integer activityLogId) {
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

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	public String getActionUser() {
		return actionUser;
	}

	public void setActionUser(String actionUser) {
		this.actionUser = actionUser;
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
	
	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public void toEntity(Object model) {
		if (model != null) {
			BeanUtils.copyProperties(model, this);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object toModel(Class clazz) {
		Object result = null;
		try {
			result = clazz.newInstance();
			BeanUtils.copyProperties(this, result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
}
