package com.mycore.thebe.testrun;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.mycore.thebe.common.util.FilingUtils;

public class CSVTest {

	public static void main(String[] args) throws Exception {
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
		
		FileWriter writer = new FileWriter("csv_test.csv");
		
		FilingUtils.createOrDownloadCSV(writer, header, contentContainer, null, null);
		
		writer.flush();
		writer.close();
		
		System.out.println("DONE");
		
		List<List<Object>> readData = new ArrayList<List<Object>>();
		
		readData = FilingUtils.readCSV("csv_test.csv", header, null, null);
		
		for (List<Object> data : readData) {
			for (Object result : data) {
				System.out.print(result.toString());
			}
			System.out.println("");
		}
	}
	
}
