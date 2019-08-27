package com.mycore.thebe.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class to validate input data
 * @author Thebe.Alfarisi
 * @since July, 16th 2019
 * @version 1.0
 *
 */
public class DataValidator {
	
	/**
	 * Method to validate name by {@link String}
	 * @param name {@link String} data to be validated
	 * @return result of data validation
	 */
	public static boolean isNameValid(String name) {
		boolean result = false;
		
		String regex = "^[\\p{L} .,'-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		
		if (matcher.matches()) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * Method to validate {@link Date} by yyyy-MM-dd format
	 * @param date {@link String} data to be validated
	 * @return result of data validation
	 */
	@SuppressWarnings("unused")
	public static boolean isDateValid(String date) {
		boolean result = false;
		
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			Date dateX = sdf.parse(date);
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to validate number
	 * @param number {@link String} data to be validated
	 * @return result of data validation
	 */
	public static boolean isNumberValid(String number) {
		boolean result = false;
		
		String regex = "^[0-9]$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(number);
		
		if (matcher.matches()) {
			result = true;
		}
		
		return result;
	}

}
