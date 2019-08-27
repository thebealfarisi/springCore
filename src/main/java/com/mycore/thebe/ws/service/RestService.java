package com.mycore.thebe.ws.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import com.mycore.thebe.common.dto.ResponseDto;
import com.mycore.thebe.common.service.CommonService;

/**
 * Service class to control Web Service process using REST
 * @author Thebe.Alfarisi
 * @since Dec, 20th 2018
 * @version 1.0
 *
 */
public interface RestService extends CommonService {
	
	/**
	 * Web Service method to create new entity from dto
	 * @param dto Dto object
	 * @param clazz target object {@link Class}
	 * @param errors {@link Errors} value
	 * @return {@link Object}[0] as {@link ResponseDto}, {@link Object}[1] as {@link HttpStatus}
	 * @throws Exception
	 */
	public Object[] createWSData(Object dto, Class<?> clazz, Errors errors) throws Exception;
	
	/**
	 * Web Service method to update entity from dto
	 * @param id {@link Object}'s primary key
	 * @param dto Dto object
	 * @param clazz target object {@link Class}
	 * @param errors {@link Errors} value
	 * @return {@link Object}[0] as {@link ResponseDto}, {@link Object}[1] as {@link HttpStatus}
	 * @throws Exception
	 */
	public Object[] updateWSData(Integer id, Object dto, Class<?> entityClazz, Errors errors) throws Exception;
	
	/**
	 * Web Service method to store (create or update) entity from dto
	 * @param id {@link Object}'s primary key
	 * @param dto Dto object
	 * @param entityClazz target object {@link Class}
	 * @param errors {@link Errors} value
	 * @return {@link Object}[0] as {@link ResponseDto}, {@link Object}[1] as {@link HttpStatus}
	 * @throws Exception
	 */
	public Object[] storeWSData(Integer id, Object dto, Class<?> entityClazz, Errors errors) throws Exception;
	
	/**
	 * Web Service method to get {@link List} {@link Object} or single {@link Object} response
	 * @param object {@link List} of {@link Object} or single {@link Object}
	 * @return {@link Object}[0] as {@link ResponseDto}, {@link Object}[1] as {@link HttpStatus}
	 * @throws Exception
	 */
	public Object[] getDataWS(Object object) throws Exception;
	
	/**
	 * Web Service method for GET request method (single or array json)
	 * @param uri web server url
	 * @return {@link String} result of retrieved {@link Object}
	 * @throws Exception
	 */
	public String methodGET(String uri) throws Exception;
	
	/**
	 * Web Service method for POST request method (single json only)
	 * @param uri web server url
	 * @param jsonData single json object
	 * @return {@link String} result of respond from web server
	 * @throws Exception
	 */
	public String methodPOST(String uri, String jsonData) throws Exception;
	
	/**
	 * Web Service method for GET request method (array json)
	 * @param uri web server url
	 * @param clazz target object {@link Class}
	 * @return {@link List} of retrieved {@link Object}
	 * @throws Exception
	 */
	public List<Object> methodGETAll(String uri, Class<?> clazz) throws Exception;

	/**
	 * Web Service method for GET request method (single json)
	 * @param uri web server url
	 * @param clazz target object {@link Class}
	 * @return single {@link Object} data
	 * @throws Exception
	 */
	public Object methodGETSingle(String uri, Class<?> clazz) throws Exception;
	
	/**
	 * Web Service method for POST request method
	 * @param uri web server url
	 * @param dataDto {@link Object}'s dto to be sent
	 * @param respondDtoClazz class of response {@link Object}
	 * @return respond's {@link Object}
	 * @throws Exception
	 */
	public Object methodPOST(String uri, Object dataDto, Class<?> respondDtoClazz) throws Exception;
	
	/**
	 * Web Service method for POST request method
	 * @param uri web server url
	 * @param dto {@link Object}'s dto to be sent
	 * @return {@link String} result of respond from web server
	 * @throws Exception
	 */
	public String methodPOST(String uri, Object dto) throws Exception;
	
}
