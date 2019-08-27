/**
 * 
 */
package com.mycore.thebe.common.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycore.thebe.common.web.WSUtils;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.RoleService;
import com.mycore.thebe.entity.service.UserService;

/**
 * Class controller to handle all auto complete form
 * @author Thebe.Alfarisi
 * @since Dec, 5th 2018
 * @version 1.0
 *
 */
@Controller
public class AutoCompleteController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value="/autoCompleteUser", method=RequestMethod.GET)
	public @ResponseBody
	List<User> getAutoCompleteUser(@RequestParam String firstName) {
		List<User> result = new ArrayList<User>();
		
		try {
			String[] columns = {"firstName", "deletedStatus"};
			Object[] params01 = {firstName, Integer.valueOf(0)};
			String[] sortColumns = {"firstName"};
			
			List<Object> objects = userService.searchData(User.class, "LIKE", columns, params01, null, true, sortColumns, 0, 10);
			
			for (Object obj : objects) {
				result.add((User) obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/autoCompleteRoleX", method=RequestMethod.GET)
	public @ResponseBody
	List<Role> getAutoCompleteRole(@RequestParam("role") String role) {
		List<Role> result = new ArrayList<Role>();
		
		try {
			String[] likeCols = {"roleName"};
			Object[] likePars = {role};
			
			String[] eqCols = {"deletedStatus"};
			Object[] eqPars = {Integer.valueOf(0)};
			
			String[] sortColumns = {"roleId"};
			
			List<Object> objects = roleService.searchDataC(Role.class, likeCols, likePars, eqCols, eqPars, null, null, null, true, sortColumns, 0, 10);
			
//			List<Object> objects = roleService.getAllData(Role.class);
			
			for (Object obj : objects) {
				result.add((Role) obj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping(value="/autoCompleteRole", method=RequestMethod.GET)
	public String getAutoCompleteRole(ModelMap model, @RequestParam("role") String role) {
//	public String getAutoCompleteRole(ModelMap model) {
		try {
			String[] likeCols = {"roleName"};
			Object[] likePars = {role};
			
			String[] eqCols = {"deletedStatus"};
			Object[] eqPars = {Integer.valueOf(0)};
			
			String[] sortColumns = {"roleId"};
			
			List<Object> objects = roleService.searchDataC(Role.class, likeCols, likePars, eqCols, eqPars, null, null, null, true, sortColumns, 0, 10);
			
			String[] desireColumns = {"roleId", "roleName"};
			
			String array = WSUtils.toJsonString(objects, desireColumns);
			
			model.addAttribute("jsonArray", array);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "autoComplete/autoCompleteAssistant";
	}
	
	
}
