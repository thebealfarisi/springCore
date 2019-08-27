package com.mycore.thebe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycore.thebe.entity.ActivityLog;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.service.ActivityLogService;
import com.mycore.thebe.entity.service.RoleMenuService;
import com.mycore.thebe.entity.service.RoleService;

/**
 * Class controller to do all testing process or data
 * @author Thebe.Alfarisi
 * @since Dec, 7th 2018
 * @version 1.0
 *
 */
@Controller
public class TestController {

	@Autowired
	RoleMenuService roleMenuService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ActivityLogService activityLogService;
	
	@RequestMapping(value="/activityLog", method=RequestMethod.GET)
	public String showActivityLog(ModelMap model) {
		List<ActivityLog> activityLogs = new ArrayList<ActivityLog>();
		
		try {
			List<Object> objects = activityLogService.getAllData(ActivityLog.class);

			for (Object obj : objects) {
				activityLogs.add((ActivityLog) obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("activityLog", new ActivityLog());
		model.addAttribute("activityLogList", activityLogs);
		
		return "activityLog/listActivityLog";
		
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String main() {
		return "index";
		
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public String showRole(ModelMap model) {
		List<Role> roles = new ArrayList<Role>();
		
		try {
			List<Object> objects = activityLogService.getAllData(Role.class);

			for (Object obj : objects) {
				roles.add((Role) obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("role", new Role());
		model.addAttribute("roleList", roles);
		
		return "role/listRole";
		
	}
	
	@RequestMapping(value="/roleMenu", method=RequestMethod.GET)
	public String showRoleMenu(ModelMap model) {
		List<RoleMenu> roleMenus = new ArrayList<RoleMenu>();
		
		try {
			List<Object> objects = activityLogService.getAllData(RoleMenu.class);

			for (Object obj : objects) {
				roleMenus.add((RoleMenu) obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("roleMenu", new RoleMenu());
		model.addAttribute("roleMenuList", roleMenus);
		
		return "roleMenu/listRoleMenu";
		
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String testProcess(ModelMap model) {
		return "test/test";
	}
	
	@RequestMapping(value="/uploadFile")
	public String uploadFile() {
		
		return "";
	}
	
	
}
