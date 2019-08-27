package com.mycore.thebe.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.mycore.thebe.common.util.DataUtils;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;

/**
 * Data Transfer Object for {@link User} to control form
 * @author Thebe.Alfarisi
 * @since Oct, 19th 2018
 * @version 1.0
 *
 */
public class UserFormDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User userEntity;
	
	//MODEL TO DTO
	public UserFormDto() {
		this.setUserEntity(new User());
	}
	public UserFormDto(User userEntity) {
		this.setUserEntity(userEntity);
	}
	
	//DTO TO MODEL
	public User getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}
	
	public String getUserId() {
		return DataUtils.getStringValue(userEntity.getUserId());
	}
	public void setUserId(String userId) {
		userEntity.setUserId(DataUtils.getIntegerValue(userId, 0));
	}
	public String getFirstName() {
		return DataUtils.getStringValue(userEntity.getFirstName());
	}
	public void setFirstName(String firstName) {
		userEntity.setFirstName(DataUtils.getStringValue(firstName));
	}
	public String getLastName() {
		return DataUtils.getStringValue(userEntity.getLastName());
	}
	public void setLastName(String lastName) {
		userEntity.setLastName(DataUtils.getStringValue(lastName));
	}
	public String getUsername() {
		return DataUtils.getStringValue(userEntity.getUsername());
	}
	public void setUsername(String username) {
		userEntity.setUsername(DataUtils.getStringValue(username));
	}
	public String getPassword() {
		return DataUtils.getStringValue(userEntity.getPassword());
	}
	public void setPassword(String password) {
		userEntity.setPassword(DataUtils.getStringValue(password));
	}
	public String getPhoneNumber() {
		return DataUtils.getStringValue(userEntity.getPhoneNumber());
	}
	public void setPhoneNumber(String phoneNumber) {
		userEntity.setPhoneNumber(DataUtils.getStringValue(phoneNumber));
	}
	public String getAddress() {
		return DataUtils.getStringValue(userEntity.getAddress());
	}
	public void setAddress(String address) {
		userEntity.setAddress(DataUtils.getStringValue(address));
	}
	public String getDescription() {
		return DataUtils.getStringValue(userEntity.getDescription());
	}
	public void setDescription(String description) {
		userEntity.setDescription(DataUtils.getStringValue(description));
	}
	public String getCreatedTime() {
		return DataUtils.getStringValue(userEntity.getCreatedTime());
	}
	public void setCreatedTime(String createdTime) {
		userEntity.setCreatedTime(Timestamp.valueOf(createdTime));
	}
	public String getCreatedBy() {
		return DataUtils.getStringValue(userEntity.getCreatedBy());
	}
	public void setCreatedBy(String createdBy) {
		userEntity.setCreatedBy(DataUtils.getStringValue(createdBy));
	}
	public String getModifiedTime() {
		return DataUtils.getStringValue(userEntity.getModifiedTime());
	}
	public void setModifiedTime(String modifiedTime) {
		userEntity.setModifiedTime(Timestamp.valueOf(modifiedTime));
	}
	public String getModifiedBy() {
		return DataUtils.getStringValue(userEntity.getModifiedBy());
	}
	public void setModifiedBy(String modifiedBy) {
		userEntity.setModifiedBy(DataUtils.getStringValue(modifiedBy));
	}
	public String getDeletedStatus() {
		return DataUtils.getStringValue(userEntity.getDeletedStatus());
	}
	public void setDeletedStatus(String deletedStatus) {
		userEntity.setDeletedStatus(DataUtils.getIntegerValue(deletedStatus, 0));
	}
	public String getRoleId() {
		String result = "";
		
		if (userEntity.getRoleId() != null) {
			result = userEntity.getRoleId().getRoleId().toString();
		}
		
		return result;
	}
	public void setRoleId(String roleId) {
		if (StringUtils.isNotBlank(roleId)) {
			Role role = new Role();
			role.setRoleId(Integer.valueOf(roleId));
			userEntity.setRoleId(role);
		}
	}

}
