package com.mycore.thebe.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * Class assistant to help DTO <-> Model conversion
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
public class DataUtils {
	
	/**
	 * Method to get string value
	 * @param param {@link Object} to get the value
	 * @param defaultVal default {@link String} set value
	 * @return {@link String} result
	 */
	public static String getStringValue(Object param, String defaultVal) {
		String result = "";
		
		result = param == null ? defaultVal : param.toString();
		
		return result;
	}
	
	/**
	 * Method to get string value
	 * @param param {@link Object} to get the value
	 * @return {@link String} result
	 */
	public static String getStringValue(Object param) {
		return getStringValue(param, "");
	}
	
	/**
	 * Method to get {@link Integer} value
	 * @param param {@link Object} to get the value
	 * @param defaultVal default {@link Integer} set value
	 * @return {@link Integer} result
	 */
	public static Integer getIntegerValue(Object param, int defaultVal) {
		String result = getStringValue(param, "" + defaultVal);
		
		if (StringUtils.isNumeric(result) && !result.trim().equals("")) {
			return new Integer(result);
		} else {
			return new Integer(0);
		}
		
	}

}
