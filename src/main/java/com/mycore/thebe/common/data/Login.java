package com.mycore.thebe.common.data;

import java.io.Serializable;

import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;
/**
 * Class to handle login process in association with {@link User} via {@link UserModel}
 * @author Thebe.Alfarisi
 * @since Nov, 29th 2018
 * @version 1.0
 *
 */
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
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

}
