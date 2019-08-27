package com.mycore.thebe.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Page Controller to control denied access to destination page
 * @author Thebe.Alfarisi
 * @since Jan, 15th 2018
 * @version 1.0
 *
 */
@Controller
public class AccessDeniedController {

	@RequestMapping(value = "/accessDenied", method=RequestMethod.GET)
	public String accessDenied() {
		return "accessDenied/accessDenied";
	}
	
}
