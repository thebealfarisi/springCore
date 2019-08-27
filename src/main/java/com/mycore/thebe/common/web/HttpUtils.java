package com.mycore.thebe.common.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.NumberUtils;

/**
 * Class to translate parsed {@link HttpServletRequest} parameter
 * @author Thebe.Alfarisi
 * @since Nov, 8th 2018
 * @version 1.0
 *
 */
public class HttpUtils {

	/**
	 * Method to convert {@link HttpServletRequest} parameter to {@link String} value
	 * @param request {@link HttpServletRequest}
	 * @param paramName parameter to be translated
	 * @param defaultValue default value
	 * @return converted {@link HttpServletRequest} parameter in {@link String}
	 */
	public static String getStringValue(HttpServletRequest request, String paramName, String defaultValue) {
		return request.getParameter(paramName) == null ? defaultValue : request.getParameter(paramName);
	}
	
	/**
	 * Method to convert {@link HttpServletRequest} parameter to {@link Integer} value
	 * @param request {@link HttpServletRequest}
	 * @param paramName parameter to be translated
	 * @return converted {@link HttpServletRequest} parameter in {@link Integer}
	 */
	public static Integer getIntegerValue(HttpServletRequest request, String paramName) {
		Integer result = null;
		String temp = getStringValue(request, paramName, "");
		
		if (NumberUtils.isNumber(temp) && !temp.trim().equalsIgnoreCase("")) {
			result = new Integer(temp);
		}
		
		return result;
	}
	
	/**
	 * Method to get sub url path
	 * @param request {@link HttpServletRequest}
	 * @return sub url path
	 * @throws Exception
	 */
	public static String getUrl(HttpServletRequest request) throws Exception {
		return request.getServletPath();
	}
	
	/**
	 * Method to get main url path
	 * @param request {@link HttpServletRequest}
	 * @return main url path
	 * @throws Exception
	 */
	public static String getContextUrl(HttpServletRequest request) throws Exception {
		return request.getContextPath();
	}
	
	/**
	 * Method to get query url path
	 * @param request {@link HttpServletRequest}
	 * @return main url path
	 * @throws Exception
	 */
	public static String getQueryPath(HttpServletRequest request) throws Exception {
		return request.getQueryString();
	}
	
}
