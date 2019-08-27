package com.mycore.thebe.common.web;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycore.thebe.common.dto.ResponseDto;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;

/**
 * Class that concern in web service's response that stored in {@link ResponseDto}
 * @author Thebe.Alfarisi
 * @since Dec, 20th 2018
 * @version 1.0
 *
 */
public class WSUtils {
	
	/**
	 * Method to proceed Web Service respond
	 * @param description respond description
	 * @param isSuccess <code>true</code> if success, <code>false</code> if fail
	 * @param respCode response code get from {@link HttpStatus}
	 * @return
	 */
	public static ResponseDto getRespond(String description, boolean isSuccess, String respCode) {
		ResponseDto result = new ResponseDto();
		
		if (isSuccess) {
			result.setStatus("success");
		} else {
			result.setStatus("fail");
		}
		
		result.setDecsription(description);
		result.setStatusCode(respCode);
		
		return result;
	}
	
	/**
	 * Method to proceed Web Service respond
	 * @param description respond description
	 * @param isSuccess <code>true</code> if success, <code>false</code> if fail
	 * @param respCode response code get from {@link HttpStatus}
	 * @param jsonString JSON {@link String}
	 * @return
	 */
	public static ResponseDto getRespondAndDetail(String description, boolean isSuccess, String respCode, String jsonString) {
		ResponseDto result = new ResponseDto();
		
		if (isSuccess) {
			result.setStatus("success");
		} else {
			result.setStatus("fail");
		}
		
		result.setDecsription(description);
		result.setStatusCode(respCode);
		result.setData(jsonString);
		
		return result;
	}
	
	/**
	 * Method to convert {@link Object} to Json {@link String}
	 * @param object {@link Object} to be converted
	 * @return Json {@link String}
	 * @throws Exception
	 */
	public static String toJson(Object object) throws Exception {
		String result = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to convert Json {@link String} to {@link Object}  
	 * @param jsonString Json {@link String} to be converter
	 * @param clazz target {@link Object}'s {@link Class}
	 * @return target {@link Object} data
	 * @throws Exception
	 */
	public static Object fromJson(String jsonString, Class<?> clazz) throws Exception {
		Object result = clazz.newInstance();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(jsonString, clazz);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to convert {@link List} of {@link Object} to Json {@link String} array
	 * @param object {@link List} of {@link Object} to be converted
	 * @return Json {@link String} array
	 * @throws Exception
	 */
	public static String toJsonArray(List<Object> objectList) throws Exception {
		String result = "";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(objectList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * BackUp (Failed Method)
	 * @param jsonListString
	 * @return
	 * @throws Exception
	 */
	public static List<Object> fromJsonArray2(String jsonListString) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Object>> mapType = new TypeReference<List<Object>>() {};
			result = mapper.readValue(jsonListString, mapType);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to convert Json {@link String} array to {@link List} of {@link Object}  
	 * @param jsonListString array ofJson {@link String}
	 * @param clazz target {@link Object}'s {@link Class}
	 * @return {@link List} of target {@link Object} data
	 * @throws Exception
	 */
	public static List<Object> fromJsonArray(String jsonListString, Class<?> clazz) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		try {
			JSONArray jsonArray = new JSONArray(jsonListString);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject objectData = jsonArray.getJSONObject(i);
				ObjectMapper mapper = new ObjectMapper();
				result.add(mapper.readValue(objectData.toString(), clazz));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to convert list of object to json array
	 * @param objectList {@link List} of {@link Object} to be converted
	 * @param desireColumns Array of column to be desired
	 * @return {@link String} value of json array
	 * @throws Exception
	 */
	public static String toJsonString(List<Object> objectList, String[] desireColumns) throws Exception {
		String result = "";
		
		try {
			JSONArray array = new JSONArray();
			for (Object object : objectList) {
				Field[] fields = object.getClass().getDeclaredFields();
				if (desireColumns.length > 0) {
					JSONObject jsonObject = new JSONObject();
					for (String column : desireColumns) {
						if (fields.length > 0) {
							for (Field field : fields) {
								field.setAccessible(true);
								if (column.equalsIgnoreCase(field.getName())) {
									jsonObject.put(column, field.get(object));
									break;
								}
							}
						}
					}
					array.add(jsonObject);
				}
			}
			
			result = array.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to convert {@link List} of {@link Object} or single {@link Object} to JSON string
	 * @param object {@link List} of {@link Object} or single {@link Object} to be converted
	 * @return converted {@link String} from {@link JSONArray} or {@link JSONObject}
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String jsonStringConversion(Object object) throws Exception {
		String result = "";
		
		try {
			if (object instanceof List<?>) {
				List<Object> tempArray = (List<Object>) object;
				result = toJsonArray(tempArray);
			} else {
				result = toJson(object);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return result;
	}
 
}
