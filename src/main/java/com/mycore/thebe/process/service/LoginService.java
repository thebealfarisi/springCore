package com.mycore.thebe.process.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.User;

/**
 * Service interface to control login/logout process
 * @author Thebe.Alfarisi
 * @since Nov, 29th 2018
 * @version 1.0
 *
 */
public interface LoginService {
	
	/**
	 * Method to do login process
	 * @param username input username data type {@link String}
	 * @param password input password data type {@link String}
	 * @param session {@link HttpSession} information
	 * @return <code>true</code> if success and <code>false</code> if fail when do login process
	 * @throws Exception
	 */
	public boolean doLogin(String username, String password, HttpSession session) throws Exception;
	
	/**
	 * Method to do logout process
	 * @param session {@link HttpSession} information
	 * @throws Exception
	 */
	public void doLogout(HttpSession session) throws Exception;
	
	/**
	 * Method to get {@link User} login data and store it data
	 * @param username input username data type {@link String}
	 * @param password input password data type {@link String}
	 * @return {@link User} data
	 * @throws Exception
	 */
	public User getUserLogin(String username, String password) throws Exception;
	
	/**
	 * Method to get {@link User} available links based on it's {@link Role}
	 * @param roleId id of {@link User}'s {@link Role}
	 * @return {@link List} of {@link RoleMenu}
	 * @throws Exception
	 */
	public List<RoleMenu> getAvailableLinks(Serializable roleId) throws Exception;

}
