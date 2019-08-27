package com.mycore.thebe.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;

/**
 * Class to validate {@link User} form data via {@link UserModel}
 * @author Thebe.Alfarisi
 * @since Nov, 29th 2018
 * @version 1.0
 *
 */
@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserModel.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		UserModel userModel = (UserModel) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "FIRST_NAME_REQUIRED", "First Name Is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "ROLE_REQUIRED", "Role Must Be Filled");
		
		//PASSWORD VALIDATION LATER
		String username = userModel.getUsername();
		String password = userModel.getPassword();
		String confPassword = userModel.getConfPassword();
		
		if (!password.equalsIgnoreCase(confPassword)) {
			errors.rejectValue("password", null, "Password Is Not Match");
		} else {
			if (password.length() < 5) {
				errors.rejectValue("password", null, "Password Length Can't Be Less Than 5 Characters");
			}
		}
		if (password.equalsIgnoreCase(username)) {
			errors.rejectValue("password", null, "Password Must Be Different Than Username");
		}
	}
	
}
