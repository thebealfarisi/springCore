package com.mycore.thebe.datamodel;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.mycore.thebe.common.model.CommonModel;
import com.mycore.thebe.entity.User;

/**
 * Class to handle {@link User} form
 * @author Thebe.Alfarisi
 * @since Nov, 21st 2018
 * @version 1.0
 *
 */
public class UserModel extends CommonModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String confPassword;
	private String phoneNumber;
	private String address;
	private String description;
	private String roleId;
	
	//UPLOAD FILE
	private MultipartFile userFile;
	
	//CHECK IS NEW
	private boolean isNew = false;
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	//
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfPassword() {
		return confPassword;
	}
	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public MultipartFile getUserFile() {
		return userFile;
	}
	public void setUserFile(MultipartFile userFile) {
		this.userFile = userFile;
	}

}
