package com.mycore.thebe.testrun;

import com.mycore.thebe.common.util.FilingUtils;

public class FileReader {

	public static void main(String[] args) throws Exception {
		String[] data = {"total.member", "total.data"};
		
		String[] result = FilingUtils.readFileProperties(data, "/usr/aink.cfg");
		
		System.out.println(result[0]);
		System.out.println(result[1]);
	}
}
