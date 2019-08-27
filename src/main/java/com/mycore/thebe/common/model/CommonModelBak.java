package com.mycore.thebe.common.model;

import java.sql.Timestamp;

/**
 * Common class that stores common model parameter for form
 * @author Thebe.Alfarisi
 * @since Nov, 21st 2018
 * @version 1.0
 *
 */
public class CommonModelBak {

	private Timestamp createdTime;
	private String createdBy;
	private Timestamp modifiedTime;
	private String modifiedBy;
	private Integer deletedStatus;
	
	public Integer getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
