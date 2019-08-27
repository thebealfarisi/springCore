package com.mycore.thebe.common.util;

/**
 * Class to manipulate {@link String} data
 * @author Thebe.Alfarisi
 * @since March, 12th 2019
 * @version 1.0
 *
 */
public class StringTextUtils {
	
	/**
	 * Method to get palindrome value from inputed {@link String}
	 * @param inputData data to be palindromed
	 * @return result of palindromed {@link String}
	 */
	public static String getPalindromeData(String inputData) {
		String result = "";
		
		int n = inputData.length();
		
		for (int i = n - 1; i >= 0; i--) {
			result = result + inputData.charAt(i);
		}
		
		return result;
	}

}
