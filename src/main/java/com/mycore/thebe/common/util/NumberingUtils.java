package com.mycore.thebe.common.util;

import java.util.Random;

/**
 * Class to generate number
 * @author Thebe.Alfarisi
 * @since March, 12th 2019
 * @version 1.0
 *
 */
public class NumberingUtils {
	
	private static Random randomNum = new Random(System.currentTimeMillis());
	
	/**
	 * Method to generate random number using luhn algorithm
	 * @param prefixNumber random number prefix
	 * @param totalLength total generated digit
	 * @return random number using luhn algorithm
	 */
	public static String generateLuhn(String prefixNumber, int totalLength) {
		int randomNumberLength = totalLength - (prefixNumber.length() + 1);
		
		StringBuilder builder = new StringBuilder(prefixNumber);
		
		for (int i = 0; i < randomNumberLength; i++) {
			int digit = randomNum.nextInt(10);
			builder.append(digit);
		}
		
		int checkDigit = getCheckDigitLuhn(builder.toString());
		builder.append(checkDigit);
		
		return builder.toString();
		
	}
	
	/**
	 * Method assistant to get check digit to create luhn random number
	 * @param number generated luhn number
	 * @return check digit
	 */
	private static int getCheckDigitLuhn(String number) {
		int sum = 0;
		for (int i = 0; i < number.length(); i++) {
			int digit = Integer.parseInt(number.substring(i, (i + 1)));
			
			if ((i % 2) == 0) {
				digit = digit * 2;
				if (digit > 9) {
					digit = (digit / 10) + (digit % 10);
				}
			}
			sum += digit;
		}
		
		int mod = sum % 10;
		return ((mod == 0) ? 0 : 10 - mod);
	}

}
