package com.mycore.thebe.testrun;

import java.util.ArrayList;
import java.util.List;

import com.mycore.thebe.common.web.WSUtils;
import com.mycore.thebe.entity.User;

public class JsonArrayString {

	public static void main(String[] args) {
		
		List<Object> users = new ArrayList<Object>();
		
		User user1 = new User();
		user1.setUserId(1);
		user1.setFirstName("AINK");
		user1.setUsername("THEBE");
		user1.setDeletedStatus(0);
		users.add(user1);
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setFirstName("KUDA");
		user2.setUsername("TERBANG");
		user2.setDeletedStatus(0);
		users.add(user2);
		
//		String[] desireColumns = {"userId", "firstName", "userName"};
		
		try {
			String result = WSUtils.jsonStringConversion(users);
			String result01 = WSUtils.jsonStringConversion(user1);
			
			System.out.println(result);
			System.out.println(result01);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
