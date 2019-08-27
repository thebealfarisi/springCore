package com.mycore.thebe.testrun;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;

public class FieldReader {
	
	
	public static void main(String[] args) {
		User user = new User();
		
		user.setUserId(1);
		user.setFirstName("THEBE");
		user.setLastName("ALFARISI");
		user.setAddress("BANDUNG");
		user.setUsername("AINK");
		user.setPassword("GANTENG");
		user.setPhoneNumber("GAGAGAGAG");
		user.setDescription("AINK PISAN");
		
		Role role = new Role();
		role.setRoleId(10);
		role.setRoleName("USER_ADMIN");
		role.setDescription("ROLE----ADMIN");
		
		user.setRoleId(role);
		
		readObject(user);
	}
	
	public static void readObject(Object object) {
		Field[] fields = object.getClass().getDeclaredFields();
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			System.out.println("Field Name: " + field.getName());
			System.out.println("Field Type: " + field.getType());
			
			if (field.getType().equals(String.class)) {
				System.out.println("FIELD TYPE STRING");
			} else if (field.getType().equals(Integer.class)) {
				System.out.println("FIELD TYPE INTEGER");
			} else if (field.getType().equals(Timestamp.class)) {
				System.out.println("FIELD TYPE TIMESTAMP");
			} else if (field.getType().equals(Role.class)) {
				System.out.println("----");
				System.out.println(field.getName().getClass().getSimpleName());
				System.out.println(field.getType().getClass().getSimpleName());
				try {
					System.out.println(field.get(object).getClass().getSimpleName());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("----");
				try {
					System.out.println("++++++");
					readObject(field.get(object));
					System.out.println("++++++");
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else {
				System.out.println("UNDEFINED YET");
			}
			
			
			try {
				System.out.println("Field Valu: " + field.get(object));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
