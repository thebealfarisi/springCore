package com.mycore.thebe.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mycore.thebe.common.data.Login;

/**
 * Class to validate login process
 * @author Thebe.Alfarisi
 * @since Nov, 29th 2018
 * @version 1.0
 *
 */
@Component
public class LoginValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Login.class.isAssignableFrom(clazz);
	}

	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "USERNAME_REQUIRED", "Username Is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "PASSWORD_REQUIRED", "Password Is Required");
		
	}

}
