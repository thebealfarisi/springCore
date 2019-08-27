package com.mycore.thebe.process.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.RoleMenuService;
import com.mycore.thebe.entity.service.UserService;
import com.mycore.thebe.process.service.LoginService;

/**
 * Service implementation of {@link LoginService} to control login/logout process
 * @author Thebe.Alfarisi
 * @since Nov, 29th 2018
 * @version 1.0
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleMenuService roleMenuService;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public boolean doLogin(String username, String password, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			String[] columns = {"username", "password", "deletedStatus"};
			Object[] params = {username, password, Integer.valueOf(0)};
			
			User user = (User) userService.getUnique(User.class, columns, params);
			
			if (user != null) {
				result = true;
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
	public void doLogout(HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public User getUserLogin(String username, String password) throws Exception {
		User result = new User();
		
		try {
			String[] columns = {"username", "password", "deletedStatus"};
			Object[] params = {username, password, Integer.valueOf(0)};
			
			result = (User) userService.getUnique(User.class, columns, params);
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
	public List<RoleMenu> getAvailableLinks(Serializable roleId) throws Exception {
		List<RoleMenu> result = new ArrayList<RoleMenu>();
		
		try {
			String[] columns = {"roleId.roleId", "deletedStatus"};
			Object[] params01 = {roleId, Integer.valueOf(0)};
			
			List<Object> objects = roleMenuService.searchData(RoleMenu.class, "EQUALS", columns, params01, null);
			
			for (Object obj : objects) {
				result.add((RoleMenu) obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
