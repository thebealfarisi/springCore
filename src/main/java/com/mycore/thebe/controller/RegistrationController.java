package com.mycore.thebe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycore.thebe.common.web.HttpUtils;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.UserService;
import com.mycore.thebe.process.service.RegistrationService;

/**
 * Page Controller that handle Registration process of {@link User}
 * @author Thebe.Alfarisi
 * @since June, 29th 2018
 * @version 1.0
 *
 */
@Controller
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@Autowired
	UserService userService;
	
	/**
	 * Method to show All {@link User} Data converted to {@link UserModel}
	 * @param model {@link UserModel} model attribute
	 * @return {@link UserModel} detail page
	 */
	@RequestMapping(value = "/user", method={RequestMethod.GET, RequestMethod.POST})
	public String showAllData(ModelMap model, HttpServletRequest request) {
		try {
			searchResult(model, request);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("user", new User());
		
		return "user/listUser";
		
	}
	
	@RequestMapping(value = "/detailUser", method=RequestMethod.GET)
	public String getDetail(HttpServletRequest request) {
		return "user/detailUser";
	}
	
	@ModelAttribute("userData")
	public User getModel(@RequestParam(value="userId", required=false) Integer userId) throws Exception {
		return userId != null ? registrationService.getUserById(userId) : new User();
	}
	
	/**
	 * assistant method for seaching process
	 * @param request {@link HttpServletRequest} 
	 * @param model {@link ModelMap}
	 * @throws Exception
	 */
	private void searchResult(ModelMap model, HttpServletRequest request) throws Exception {
		List<User> users = new ArrayList<User>();
		
		//MAIN PAGING OPTION
		int index = HttpUtils.getIntegerValue(request, "index") == null ? Integer.valueOf(0) : HttpUtils.getIntegerValue(request, "index");
		String searchBy = HttpUtils.getStringValue(request, "searchBy", "");
		String searchVal = HttpUtils.getStringValue(request, "searchVal", "");
		String between = HttpUtils.getStringValue(request, "between", "");
		String betweenVal01 = HttpUtils.getStringValue(request, "betweenVal01", "");
		String betweenVal02 = HttpUtils.getStringValue(request, "betweenVal02", "");
		String navigation = HttpUtils.getStringValue(request, "nav", "");
		
		//ADDITIONAL PAGING OPTION
		
		int maxValue = 5;
		int maxIndex = 0;
		int initialValue = 0;
		int totalData = 0;
		
		double tempMaxIndex = 0.0;
		int tempRoundValue = 0;
		 
		try {
			//---SEARCH OPTION---//
			//Like//
			List<String> likeColsTemp = new ArrayList<String>();
			List<Object> likeParsTemp = new ArrayList<Object>();
			//Equals//
			List<String> eqColsTemp = new ArrayList<String>();
			List<Object> eqParsTemp = new ArrayList<Object>();
			//Between//
			List<String> betweenColsTemp = new ArrayList<String>();
			List<Object> betweenPars01Temp = new ArrayList<Object>();
			List<Object> betweenPars02Temp = new ArrayList<Object>();
			
			//---SEARCH VALUE---//
			//Like//
			if (StringUtils.isNotBlank(searchBy)) {
				likeColsTemp.add(searchBy);
				likeParsTemp.add(searchVal);
			}
			//Equals//

			//Between//
			if (StringUtils.isNotBlank(between)) {
				betweenColsTemp.add(between);
				betweenPars01Temp.add(betweenVal01);
				betweenPars02Temp.add(betweenVal02);
			}

			//Constant Value
			eqColsTemp.add("deletedStatus");
			eqParsTemp.add(Integer.valueOf(0));
			
			//---COMPILE SEARCH FUNCTION---//
			//Like//
			String[] likeCols = new String[likeColsTemp.size()];
			likeColsTemp.toArray(likeCols);
			Object[] likePars = new Object[likeParsTemp.size()];
			likeParsTemp.toArray(likePars);
			//Equals//
			String[] eqCols = new String[eqColsTemp.size()];
			eqColsTemp.toArray(eqCols);
			Object[] eqPars = new Object[eqParsTemp.size()];
			eqParsTemp.toArray(eqPars);
			//Between//
			String[] betweenCols = new String[betweenColsTemp.size()];
			betweenColsTemp.toArray(betweenCols);
			Object[] betweenPars01 = new Object[betweenPars01Temp.size()];
			betweenPars01Temp.toArray(betweenPars01);
			Object[] betweenPars02 = new Object[betweenPars02Temp.size()];
			betweenPars02Temp.toArray(betweenPars02);
			
			//sorting columns
			String[] ascColumns = {"userId"};
			
			//total retrieved data
			initialValue = maxValue * index;
			
			//SEARCHING PROCESS
			totalData = registrationService.getTotalData(likeCols, likePars, eqCols, eqPars, betweenCols, betweenPars01, betweenPars02);
			users = registrationService.search(likeCols, likePars, eqCols, eqPars, betweenCols, betweenPars01, betweenPars02, true, ascColumns, initialValue, maxValue);
			
			//INDEXING
			tempMaxIndex = ((double) totalData)/((double) maxValue);
			tempRoundValue = (int) Math.floor(totalData)/(maxValue);
			
			if (tempMaxIndex > tempRoundValue) {
				maxIndex = Math.round(totalData/maxValue);
			} else {
				maxIndex = (Math.round(totalData/maxValue)) - 1;
			}
			
			//INDEXING NAV
			if (StringUtils.isNotBlank(navigation)) {
				if (navigation.equalsIgnoreCase("prev")) {
					index -= 1;
				} else if (navigation.equalsIgnoreCase("next")) {
					index += 1;
				} else if (navigation.equalsIgnoreCase("start")) {
					index = 0;
				} else if (navigation.equalsIgnoreCase("end")) {
					index = maxIndex;
				}
			}
			
			//LIMITING
			if (index < 0) {
				index = 0;
			} else if (index > maxIndex) {
				index = maxIndex;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		model.addAttribute("userList", users);
		model.addAttribute("totalData", totalData);
		
		model.addAttribute("searchBy", searchBy);
		model.addAttribute("searchVal", searchVal);
		model.addAttribute("index", index);
		model.addAttribute("maxIndex", maxIndex);
		model.addAttribute("maxValue", maxValue);
	}

}
