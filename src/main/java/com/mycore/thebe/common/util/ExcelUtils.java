package com.mycore.thebe.common.util;

import java.sql.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * Class to convert cell value as desire type data
 * @author Thebe.Alfarisi
 * @since Dec, 18th 2018
 * @version 1.0
 *
 */
public class ExcelUtils {

	/**
	 * Method to convert cell value as {@link String}
	 * @param cell {@link Cell} data
	 * @return {@link String} type of data
	 * @throws Exception
	 */
	public static String getValueAsString(Cell cell) throws Exception {
		String result = "";
		
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return result;
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				result = (cell.getDateCellValue().toString()).trim();
			} else {
				result = (cell.getNumericCellValue() + "").trim();
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			result = (cell.getRichStringCellValue().getString()).trim();
		} 
		
		return result;
	}
	
	/**
	 * Method to convert cell value as {@link Double} numeric
	 * @param cell {@link Cell} data
	 * @return {@link Double} numeric type of data
	 * @throws Exception
	 */
	public static Double getValueAsNumeric(Cell cell) throws Exception {
		Double result = 0.0;
		
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				result = cell.getNumericCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				try {
					result = Double.valueOf(cell.getStringCellValue().trim());
				} catch (Exception e) {
					// TODO: handle exception
					return result;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Method to convert cell value as {@link Date}
	 * @param cell {@link Cell} data
	 * @return {@link Date} type of data
	 * @throws Exception
	 */
	public static Date getValueAsDate(Cell cell) throws Exception {
		Date result = null;
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return result;
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				result = new Date(cell.getDateCellValue().getTime());
			}
		}
		
		return result;
	}
	
	/**
	 * Method to convert cell value as boolean {@link Integer} value of 1 or 0
	 * @param cell {@link Cell} data
	 * @return {@link Integer} type of data
	 * @throws Exception
	 */
	public static Integer getValueAsBoolean(Cell cell) throws Exception {
		Integer result = 0;
		if(cell != null) {
			if (getValueAsString(cell).trim().toUpperCase().equalsIgnoreCase("Y")) {
				result = 1;
			}
		}
		
		return result;
	}
	
}
