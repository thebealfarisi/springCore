package com.mycore.thebe.testrun;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.mycore.thebe.common.util.FilingUtils;

public class ExcelTest {

	public static void main(String[] args) throws Exception{
		List<Object> header = new ArrayList<Object>();
		List<List<Object>> contentContainer = new ArrayList<List<Object>>();
		List<Object> content1 = new ArrayList<Object>();
		List<Object> content2 = new ArrayList<Object>();
		List<Object> content3 = new ArrayList<Object>();
		
		header.add("No");
		header.add("Name");
		
		content1.add("1");
		content1.add("A");
		contentContainer.add(content1);
		
		content2.add("2");
		content2.add("B");
		contentContainer.add(content2);
		
		content3.add("3");
		content3.add("C");
		contentContainer.add(content3);
		
		
		HSSFWorkbook workbook = FilingUtils.createOrDownloadExcel(header, contentContainer);
		
		FileOutputStream outputStream = new FileOutputStream("excel_test.xls");
		workbook.write(outputStream);
		System.out.println("DONE");
		
		
		
		List<List<Object>> readData = new ArrayList<List<Object>>();
		
		readData = FilingUtils.readExcel("excel_test.xls", header);
		
		for (List<Object> data : readData) {
			for (Object result : data) {
				System.out.print(result.toString());
			}
			System.out.println("");
		}
		
	}
	
	
	
	
}
