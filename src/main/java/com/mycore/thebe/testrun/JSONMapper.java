package com.mycore.thebe.testrun;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycore.thebe.common.conversion.DTOConversion;
import com.mycore.thebe.common.web.WSUtils;
import com.mycore.thebe.dto.UserDto;
import com.mycore.thebe.entity.User;

public class JSONMapper {

	public static void main(String[] args) {
		try {
			User user = new User();
			user.setUserId(1);
			user.setFirstName("thebe");
			user.setLastName("aink");
			UserDto userDto = new UserDto();
			userDto = (UserDto) DTOConversion.toDto(userDto, user);
			//TOJSON
			String json = WSUtils.toJson(user);
			System.out.println(json);
			System.out.println("");
			user = new User();
			//FROM JSON
			userDto = (UserDto) WSUtils.fromJson(json, UserDto.class);
			System.out.println(userDto.getUserId());
			System.out.println(userDto.getFirstName());
			System.out.println(userDto.getLastName());
			System.out.println("");
			
			
			User user1 = new User();
			User user2 = new User();
			List<Object> userList = new ArrayList<Object>();
			user1.setUserId(1);
			user1.setFirstName("thebe");
			user1.setLastName("aink");
			userList.add(user1);
			user2.setUserId(1);
			user2.setFirstName("kuda");
			user2.setLastName("terbang");
			userList.add(user2);
			//TOJSONLIST
			String jsonList = WSUtils.toJsonArray(userList);
			System.out.println("COLLECTION");
			System.out.println(jsonList);
			System.out.println("");
			//FROMTOJSON
			//METHOD 1
			List<User> users = new ArrayList<User>();
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> mapType = new TypeReference<List<User>>() {};
			users = mapper.readValue(jsonList, mapType);
			for (User user3 : users) {
				
				System.out.println(user3.getUserId());
				System.out.println(user3.getFirstName());
				System.out.println(user3.getLastName());
			}
					
			//METHOD 2
			userList = WSUtils.fromJsonArray(jsonList, User.class);
			
			for (Object user3 : userList) {
				User data = (User) user3;
				
				System.out.println(data.getUserId());
				System.out.println(data.getFirstName());
				System.out.println(data.getLastName());
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
