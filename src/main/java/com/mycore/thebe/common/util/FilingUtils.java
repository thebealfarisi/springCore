package com.mycore.thebe.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class to control all filing process
 * @author Thebe.Alfarisi
 * @since Dec, 17th 2018
 * @version 1.0
 *
 */
public class FilingUtils {

	/**
	 * Method to move byte of {@link File} to desire destination
	 * @param fileContent byte array of {@link File} content
	 * @param fileName name of {@link File} to be saved
	 * @param location target {@link File} location
	 * @throws Exception
	 */
	public static void moveFile(byte[] fileContent, String fileName, String location) throws Exception {
		File targetLocation = new File(location);
		if (!targetLocation.exists()) {
			targetLocation.mkdirs();
		}
		
		Path path = Paths.get(targetLocation + File.separator + fileName);
		
		Files.write(path, fileContent);
		
	}
	
	/**
	 * Method to move {@link File} to desire destination
	 * @param file {@link File} to be moved
	 * @param location target {@link File} location
	 * @throws Exception
	 */
	public static void moveFile(File file, String location) throws Exception {
		File targetLocation = new File(location);
		if (!targetLocation.exists()) {
			targetLocation.mkdirs();
		}
		
		byte[] fileContent = readBytesFromFile(file.toString());
		
		Path path = Paths.get(targetLocation + File.separator + file.getName());
		
		Files.write(path, fileContent);
	}
	
	/**
	 * method to read byte of {@link File}
	 * @param filePath {@link File} location
	 * @return byte array of {@link File}
	 * @throws Exception
	 */
	public static byte[] readBytesFromFile(String filePath) throws Exception {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		
		try {
			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		
		return bytesArray;
	}
	
	/**
	 * method to read byte of {@link File} or {@link MultipartFile} 
	 * @param obj {@link Object} of {@link File} or {@link MultipartFile} 
	 * @return byte array of {@link Object}
	 * @throws Exception
	 */
	public static byte[] readBytesFromFileOrMultipartFile(Object obj) throws Exception {
		byte[] bytesArray = null;
		
		try {
			if (obj instanceof File) {
				bytesArray = new byte[(int) ((File) obj).length()];
			} else if (obj instanceof MultipartFile) {
				bytesArray = ((MultipartFile) obj).getBytes();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return bytesArray;
	}
	
	/**
	 * Method to upload files
	 * @param files {@link MultipartFile} data
	 * @param location target location
	 * @throws Exception
	 */
	public static void uploadFile(MultipartFile[] files, String location) throws Exception {
		File targetLocation = new File(location);
		if (!targetLocation.exists()) {
			targetLocation.mkdirs();
		}
		
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}
			
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(targetLocation + File.separator + file.getOriginalFilename());
				
				Files.write(path, bytes);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method to read file property
	 * @param propertiesNames {@link List} of desire file properties
	 * @param file {@link File} of the property
	 * @return {@link String}[] of retrieved properties
	 */
	public static String[] readFileProperties(String[] propertiesNames, String file) {
		int totalData = propertiesNames.length;
		String[] result = new String[totalData];
		
		try {
			FileReader propertiesFile = new FileReader(file);
			if (propertiesFile != null) {
				Properties prop = new Properties();
				prop.load(propertiesFile);
				
				int i = 0;
				for (String propertiesName : propertiesNames) {
					String propertiesData = prop.getProperty(propertiesName) == null ? "" : prop.getProperty(propertiesName);
					result[i] = propertiesData;
					i++;
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to get file template
	 * @param templateFile {@link File} template source
	 * @return {@link Template} file data
	 * @throws Exception
	 */
	public static Template getTemplate(String templateFile) throws Exception {
		Template template = new Template();
		
		try {
			Properties properties = new Properties();
			properties.setProperty( "resource.loader", "class" );
			properties.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
			properties.setProperty( "resource.loader", "file" );
			properties.setProperty( "file.resource.loader.path", "" );
			
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);
			
			template = velocityEngine.getTemplate(templateFile);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return template;
	}
	
	/**
	 * Method to create or download excel file
	 * @param headerNames {@link List} of header
	 * @param dataObjects nested {@link List} of data
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook createOrDownloadExcel(List<Object> headerNames, List<List<Object>> dataObjects) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		try {
			HSSFSheet sheet = workbook.createSheet();
			
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setBorderTop((short) 1); 
	        cellStyle.setBorderBottom((short) 1); 
	        cellStyle.setBorderLeft((short)1);
	        cellStyle.setBorderRight((short)1);
	        
	        HSSFRow header = sheet.createRow(0);
	       
	        int columnHeader = 0;
	        for (Object headerName : headerNames) {
	        	
	        	HSSFCell cellHeader = header.createCell((short)columnHeader);
	        	cellHeader.setCellValue(headerName.toString());
	        	cellHeader.setCellStyle(cellStyle);
	        	
	        	columnHeader++;
	        }
	        
	        int rowData = 1;
	        for (List<Object> dataObject : dataObjects) {
	        	HSSFRow data = sheet.createRow(rowData);
	        	int columnData = 0;
	        	for (Object object : dataObject) {
	        		HSSFCell cellData = data.createCell((short)columnData);
	        		cellData.setCellValue(String.valueOf(object));
	        		cellData.setCellStyle(cellStyle);
		        	columnData++;
	        	}
	        	rowData++;
	        }
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return workbook;
	}
	
	/**
	 * Method to read excel file
	 * @param file file to be read
	 * @param header {@link List} of header data
	 * @return nested {@link List} of excel data
	 * @throws Exception
	 */
	public static List<List<Object>> readExcel(String file, List<Object> header) throws Exception {
		List<List<Object>> result = new ArrayList<List<Object>>();
		
		try {
			FileInputStream readFile = new FileInputStream(file);
			
			HSSFWorkbook workbook = new HSSFWorkbook(readFile);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			Iterator<Row> rows = null;
			rows = sheet.iterator();
			
			int rowData = 0;
			
			while (rows.hasNext()) {
				Row row = rows.next();
				
				if (rowData > 0) {
					List<Object> data = new ArrayList<Object>();
					int totalColumn = header.size();
					
					for (int i = 0; i < totalColumn; i++) {
						data.add(ExcelUtils.getValueAsString(row.getCell(i, Row.CREATE_NULL_AS_BLANK)));
					}
					result.add(data);
				}
				rowData++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to create or download csv file
	 * @param writer {@link Writer} io
	 * @param header {@link List} of header
	 * @param contents nested {@link List} of csv data content
	 * @param separator data separator, if set null the separator value will be (,) (default value)
	 * @param customQuote data custom quote, if set null the separator value will be (") (default value)
	 * @throws Exception
	 */
	public static void createOrDownloadCSV(Writer writer, List<Object> header, List<List<Object>> contents, String separator, String customQuote) throws Exception {
		String SEPARATOR = ",";
		String CUSTOM_QUOTE = "\"";
		
		if (separator != null) {
			SEPARATOR = separator;
		}
		if (customQuote != null) {
			CUSTOM_QUOTE = customQuote;
		}
		
		//header
		csvAssistant01(writer, header, SEPARATOR, CUSTOM_QUOTE);
		
		//content
		for (List<Object> content : contents) {
			csvAssistant01(writer, content, SEPARATOR, CUSTOM_QUOTE);
		}
		
	}
	
	/**
	 * Method to read csv file
	 * @param file file to be read
	 * @param header {@link List} of header
	 * @param separator data separator, if set null the separator value will be (,) (default value)
	 * @param customQuote data custom quote, if set null the separator value will be (") (default value)
	 * @return nested {@link List} of csv data
	 * @throws Exception
	 */
	public static List<List<Object>> readCSV (String file, List<Object> header, String separator, String customQuote) throws Exception {
		List<List<Object>> result = new ArrayList<List<Object>>();
		
		String SEPARATOR = ",";
		String CUSTOM_QUOTE = "\"";
		String REPLACED_CHAR = "";
		
		if (separator != null) {
			SEPARATOR = separator;
		}
		if (customQuote != null) {
			CUSTOM_QUOTE = customQuote;
		}
		
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line = "";
			
			if (br.readLine() != null) {
				//SKIP HEADER
			}
			
			while ((line = br.readLine()) != null) {
				String[] temps = line.split(SEPARATOR);
				List<Object> data = new ArrayList<Object>();
				for (String temp : temps) {
					data.add(temp.replace(CUSTOM_QUOTE, REPLACED_CHAR));
				}
				result.add(data);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (br != null) {
                try {
                    br.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
		}
		
		return result;
	}
	
	/**
	 * Method assistant of createOrDownloadCSV method to insert data to csv
	 * @param writer {@link Writer} io
	 * @param values {@link List} of data to be writen
	 * @param separator data separator, if set null the separator value will be (,) (default value)
	 * @param customQuote data custom quote, if set null the separator value will be (") (default value)
	 * @throws Exception
	 */
	private static void csvAssistant01(Writer writer, List<Object> values, String separator, String customQuote) throws Exception {
		boolean first = true;
		
		StringBuilder sb = new StringBuilder();
		for (Object value : values) {
			if  (!first) {
				sb.append(separator);
			}
			if (customQuote.equals(" ")) {
				sb.append(csvAssistant02(String.valueOf(value)));
			} else {
				sb.append(customQuote).append(csvAssistant02(String.valueOf(value))).append(customQuote);
			}
			
			first = false;
		}
		sb.append("\r\n");
		writer.append(sb.toString());
	}
	
	/**
	 * Method assistant of createOrDownloadCSV method to follow csv format
	 * @param value data to be writen
	 * @return data to be writen
	 */
	private static String csvAssistant02(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
	
	public static void createOrDownloadTXT(String content) {
		
	}

	public static void createOrDownloadPDF() {
		
	}
	
}
