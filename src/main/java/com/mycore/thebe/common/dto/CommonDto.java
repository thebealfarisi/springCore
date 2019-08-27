package com.mycore.thebe.common.dto;

/**
 * Common class for data transfer object via REST service
 * @author Thebe.Alfarisi
 * @since Dec, 13th 2018
 * @version 1.0
 *
 */
public class CommonDto {

	private String createdTime;
	private String createdBy;
	private String modifiedTime;
	private String modifiedBy;
	private String deletedStatus;
	
	public String getDeletedStatus() {
		return deletedStatus;
	}
	public void setDeletedStatus(String deletedStatus) {
		this.deletedStatus = deletedStatus;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
}
