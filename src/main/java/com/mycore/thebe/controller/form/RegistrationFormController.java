package com.mycore.thebe.controller.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.form.validator.UserValidator;
import com.mycore.thebe.process.service.RegistrationService;
import com.mycore.thebe.process.service.SessionSecurityService;

/**
 * {@link Controller} class to control registration form process
 * @author Thebe.Alfarisi
 * @since unrecorded
 * @version 1.0
 *
 */
@Controller
public class RegistrationFormController {
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	SessionSecurityService sessionSecurityService;
	
	@RequestMapping(value="/editUser", method=RequestMethod.GET) 
	public String formCreator(HttpServletRequest request) {
		return "user/editUser";
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.POST)
	public String saveData(@ModelAttribute("userData") UserModel model, @RequestParam("file") MultipartFile file, BindingResult result, HttpServletRequest request) {
		
		UserValidator userValidator = new UserValidator();
		userValidator.validate(model, result);
		String location = "";
		
		try {
			if (result.hasErrors()){
				location = "user/editUser";
	        } else {
	        	boolean toProcess = false;
	        	User dataChecker = registrationService.getUserByUserName(model.getUsername());
	        	if (model.isNew()) {
		        	if (dataChecker == null) {
		        		toProcess = true;
		        	}
	        	} else {
	        		boolean isUnique = registrationService.checkUserName(model.getUsername());
	        		if (isUnique) {
	        			if ((dataChecker.getUserId()).toString().equalsIgnoreCase(model.getUserId())) {
		        			toProcess = true;
		        		}
	        		}
	        	}
	        	
	        	if (toProcess) {
	        		ActionUser actionUser = sessionSecurityService.getActionUser(request);
		    		registrationService.storeUser(model, file, actionUser, request);		
//		    		sessionSecurityService.recordLog(request, "create or update userId=" + model.getUserId());
		    		location = "redirect:/user";
	        	} else {
	        		result.rejectValue("username", null, "Username Already Taken");
	        		location = "user/editUser";
	        	}
	        }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return location;
	}
	
	@ModelAttribute("userData")
	public UserModel getModel(@RequestParam(value="userId", required=false) Integer userId) throws Exception {
		UserModel result = new UserModel();
		if (userId != null) {
			User resultTemp = registrationService.getUserById(userId);
			result = (UserModel) resultTemp.toModel(UserModel.class);
		} else {
			result.setNew(true);
		}
		return result;
	}
	
	@ModelAttribute("roleData")
	public List<Role> getAllRoles() throws Exception {
		return registrationService.getAllRoleData();
	}
	
}
