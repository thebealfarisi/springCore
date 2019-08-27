package com.mycore.thebe.controller.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycore.thebe.common.data.Login;
import com.mycore.thebe.common.web.HttpUtils;
import com.mycore.thebe.entity.RoleMenu;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.form.validator.LoginValidator;
import com.mycore.thebe.process.service.LoginService;
import com.mycore.thebe.process.service.SessionSecurityService;

/**
 * {@link Controller} class to control login and logout process
 * @author Thebe.Alfarisi
 * @since Nov, 23rd 2018
 * @version 1.0
 *
 */
@Controller
public class LoginFormController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	SessionSecurityService sessionSecurityService;

	@RequestMapping(value={"/login", "/logout"}, method=RequestMethod.GET)
	public String formCreator(HttpServletRequest request, ModelMap modelMap) throws Exception {
		HttpSession session = request.getSession(false);
		String requestUrl = HttpUtils.getUrl(request);
		modelMap.addAttribute("loginData", new Login());
		
		try {
			if (requestUrl.equalsIgnoreCase("/login")) {
				
			} else if (requestUrl.equalsIgnoreCase("/logout")) {
				if (session != null) {
					sessionSecurityService.clearSessionAttributes(session);
					session.invalidate();
					sessionSecurityService.recordLog(request, "logout");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "login/login";
	}
	
	@RequestMapping(value="/doLogin", method=RequestMethod.POST)
	public String doProcess(@ModelAttribute("loginData") Login model, BindingResult result, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		HttpSession session = request.getSession(false);
		String location = "";
		
		try {
			LoginValidator loginValidator = new LoginValidator();
			loginValidator.validate(model, result);
			
			if (result.hasErrors()) {
				location = "login/login";
			} else {
				User user = loginService.getUserLogin(model.getUsername(), model.getPassword());
				if (user == null) {
					modelMap.addAttribute("loginStatus", "Wrong Username Or Password");
					location = "login/login";
				} else {
					if (session != null) {
						
						session.setAttribute("sessionUser", user);
						session.setAttribute("sessionIp", request.getRemoteAddr());
						session.setAttribute("sessionId", session.getId());
						
						if (user.getRoleId() != null) {
							List<RoleMenu> roleMenus = loginService.getAvailableLinks(user.getRoleId().getRoleId()); 
							session.setAttribute("availableLinks", roleMenus);
						}
						
						sessionSecurityService.recordLog(request, "login");
						
						location = "redirect:" + user.getRoleId().getMainPage();
					} else {
						location = "login/login";
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return location;
	
	}

}
