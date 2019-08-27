package com.mycore.thebe.common.data;

import java.io.Serializable;

import com.mycore.thebe.entity.User;

/**
 * Data Model that store {@link User} information for data activity
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
public class ActionUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user;

	//-----Setter Getter-----
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
