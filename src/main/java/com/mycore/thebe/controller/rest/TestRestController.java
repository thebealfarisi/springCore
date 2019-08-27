package com.mycore.thebe.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycore.thebe.common.conversion.DTOConversion;
import com.mycore.thebe.common.dto.ResponseDto;
import com.mycore.thebe.dto.UserDto;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.ws.service.RestService;

/**
 * Rest Controller for testing
 * @author Thebe.Alfarisi
 * @since Dec, 11th 2018
 * @version 1.0
 *
 */
@RestController
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestRestController {
	
	@Autowired
	RestService restService;
	
	//GET ALL DATA
	//--ver01
	@RequestMapping(value="/restUser", method=RequestMethod.GET)
	public ResponseEntity<List<UserDto>> listAllUsers() {
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			List<Object> objects = restService.getAllData(User.class);
			
			for (Object obj : objects) {
				UserDto user = new UserDto();
				users.add((UserDto) DTOConversion.toDto(user, (User) obj));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}
	
	//--ver02
	@RequestMapping(value="/restUser01", method=RequestMethod.GET)
	public ResponseEntity<ResponseDto> listAllUsers01() {
		List<UserDto> users = new ArrayList<UserDto>();
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		try {
			List<Object> objects = restService.getAllData(User.class);
			
			for (Object obj : objects) {
				UserDto user = new UserDto();
				users.add((UserDto) DTOConversion.toDto(user, (User) obj));
			}
			
			Object[] object = restService.getDataWS(users);
			
			responseDto = (ResponseDto) object[0];
			httpStatus = (HttpStatus) object[1];
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
	}
	
	//GET USER DATA
	//--ver01
	@RequestMapping(value="/user01_{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> userDetail01(@PathVariable("id") Integer id) {
		UserDto userDto = new UserDto();
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		Object[] object = null;
		try {
			User temp = (User) restService.getData(id, User.class);
			if (temp != null) {
				userDto = (UserDto) DTOConversion.toDto(userDto, temp);
				object = restService.getDataWS(userDto);
			} else {
				object = restService.getDataWS(null);
			}
			
			responseDto = (ResponseDto) object[0];
			httpStatus = (HttpStatus) object[1];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
	}
	
	//--ver02
	@RequestMapping(value="/user_{id}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> userDetail(@PathVariable("id") Integer id) {
		UserDto userDto = new UserDto();
		HttpStatus httpStatus = null;
		try {
			User temp = (User) restService.getData(id, User.class);
			if (temp != null) {
				userDto = (UserDto) DTOConversion.toDto(userDto, temp);
				httpStatus = HttpStatus.OK;
			} else {
				httpStatus = HttpStatus.NOT_FOUND;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<UserDto>(userDto, httpStatus);
	}
	
	//CREATE USER
	@RequestMapping(value="/restUser", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto) {
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		try {
			Object[] object = restService.createWSData(userDto, User.class, null);
			responseDto = (ResponseDto) object[0];
			httpStatus = (HttpStatus) object[1];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
	}
	
	//UPDATE USER
	@RequestMapping(value="/user_{id}", method=RequestMethod.PUT)
	public ResponseEntity<ResponseDto> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		try {
			Object[] object = restService.updateWSData(id, userDto, User.class, null);
			responseDto = (ResponseDto) object[0];
			httpStatus = (HttpStatus) object[1];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
	}
	
	//STORE USER --skip masalah validasi
	@RequestMapping(value="/storeUser_{id}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseDto> storeUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		try {
			
			Object[] object = restService.storeWSData(id, userDto, User.class, null);
			responseDto = (ResponseDto) object[0];
			httpStatus = (HttpStatus) object[1];
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseDto>(responseDto, httpStatus);
	}
	
	//BACK UP
//	@RequestMapping(value="/user_{id}", method=RequestMethod.PUT)
//	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto) {
//		try {
//			User temp = (User) restService.getData(id, User.class);
//			temp = (User) DTOConversion.fromDto(userDto, temp);
//			restService.store(temp);
//			
//			userDto = (UserDto) DTOConversion.toDto(userDto, temp);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//	}

}
