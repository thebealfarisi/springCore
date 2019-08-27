package com.mycore.thebe.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class to mapping template with {@link Object} model
 * @author Thebe.Alfarisi
 * @since Feb, 8th 2016
 * @version 1.0
 * 
 */
public class Mapping {

	/**
	 * Method to create excel mapping from template to {@link Object}
	 * @param object {@link Object} to be mapped
	 * @param template Template data
	 * @param delimiter Delimiter
	 * @param workbook Excel data
	 * @return {@link Object} mapped result
	 * @throws Exception
	 */
	public Object doMapping(Object object, String template, String delimiter, HSSFWorkbook workbook) throws Exception {
		
		try {
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			
			int rowNum = 0;
			
			while (rows.hasNext()) {
				Row row = rows.next();
				StringTokenizer tokens = new StringTokenizer(template, delimiter);
				Field[] modelFields = object.getClass().getDeclaredFields();
				
				if (rowNum > 0) {
					int cellNum = 0;
				
					while (tokens.hasMoreTokens()) {
						String header = tokens.nextToken();
						for (Field modelField : modelFields) {
							modelField.setAccessible(true);
							if (modelField.getName().equalsIgnoreCase(header)) {
								Cell cell = row.getCell(cellNum);
								String value = "";
								if (cell != null) {
									int type = cell.getCellType();
									if (type == Cell.CELL_TYPE_NUMERIC) {
										if (DateUtil.isCellDateFormatted(cell)) {
											java.util.Date dateVal = cell.getDateCellValue();
											if (dateVal != null) {
												Date sqlDate = new Date(dateVal.getTime());
												value = sqlDate.toString();
											}
										} else {
											double num = cell.getNumericCellValue();
											BigDecimal bigDec = new BigDecimal(num);
											value = bigDec.toPlainString();
										}
									}
									if (type == Cell.CELL_TYPE_BOOLEAN) {
										value = cell.getBooleanCellValue() + "";
									}
									if (type == Cell.CELL_TYPE_STRING) {
										value = cell.getStringCellValue();
									}
								}
								modelField.set(object, value);
							}
							break;
						}
						cellNum++;
					}
					rowNum++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return object;
	}
	
}
