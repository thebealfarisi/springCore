/**
 * 
 */
package com.mycore.thebe.ws.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.mycore.thebe.common.conversion.DTOConversion;
import com.mycore.thebe.common.dto.ResponseDto;
import com.mycore.thebe.common.service.impl.CommonServiceImpl;
import com.mycore.thebe.common.web.WSUtils;
import com.mycore.thebe.ws.service.RestService;

/**
 * Implementation of {@link RestService}
 * @author Thebe.Alfarisi
 * @since Dec, 20th 2018
 * @version 1.0
 *
 */
@Service("restService")
public class RestServiceImpl extends CommonServiceImpl implements RestService {

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object[] createWSData(Object dto, Class<?> entityClazz, Errors errors) throws Exception {
		// TODO Auto-generated method stub
		Object[] result = new Object[2];
		
		Object entity = entityClazz.newInstance();
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;

		boolean toProcess = false;
		String description = "";
		try {
			
			if (errors == null) {
				toProcess = true;
			} else {
				if ((!errors.hasErrors())) {
					toProcess = true;
				}
			}
			
			if (toProcess) {
				entity = DTOConversion.fromDto(dto, entity);
				this.store(entity);
				httpStatus = HttpStatus.CREATED;
				description = "DATA CREATED";
			} else {
				httpStatus = HttpStatus.CONFLICT;
				description = errors.toString();
			}
			
			responseDto = WSUtils.getRespond(description, toProcess, httpStatus.toString());
			
			result[0] = responseDto;
			result[1] = httpStatus;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object[] updateWSData(Integer id, Object dto, Class<?> entityClazz, Errors errors) throws Exception {
		// TODO Auto-generated method stub
		Object[] result = new Object[2];
		
		Object entity = entityClazz.newInstance();
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;

		boolean toProcess = false;
		String description = "";
		try {
			
			if (errors == null) {
				toProcess = true;
			} else {
				if ((!errors.hasErrors())) {
					toProcess = true;
				}
			}
			
			if (toProcess) {
				entity = this.getData(id, entityClazz);
				
				if (entity != null) {
					entity = DTOConversion.fromDto(dto, entity);
					this.store(entity);
					httpStatus = HttpStatus.OK;
					description = "DATA UPDATED";
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					description = "DATA NOT FOUND";
					toProcess = false;
				}
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				description = errors.toString();
			}
			
			responseDto = WSUtils.getRespond(description, toProcess, httpStatus.toString());
			
			result[0] = responseDto;
			result[1] = httpStatus;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object[] storeWSData(Integer id, Object dto, Class<?> entityClazz, Errors errors) throws Exception {
		// TODO Auto-generated method stub
		Object[] result = new Object[2];
		
		Object entity = entityClazz.newInstance();
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		
		boolean toProcess = false;
		String description = "";
		String jsonString = "";
		
		try {
			if (errors == null) {
				toProcess = true;
			} else {
				if ((!errors.hasErrors())) {
					toProcess = true;
				}
			}
			
			if (toProcess) {
				if (id != null) {
					entity = this.getData(id, entityClazz);
					entity = DTOConversion.fromDto(dto, entity);
					httpStatus = HttpStatus.OK;
					description = "DATA UPDATED";
				} else {
					entity = DTOConversion.fromDto(dto, entity);
					httpStatus = HttpStatus.OK;
					description = "DATA CREATED";
				}
				entity = this.store(entity, null);
				jsonString = WSUtils.jsonStringConversion(entity);
			} else {
				httpStatus = HttpStatus.BAD_REQUEST;
				description = errors.toString();
			}
			
			responseDto = WSUtils.getRespondAndDetail(description, toProcess, httpStatus.toString(), jsonString);
			
			result[0] = responseDto;
			result[1] = httpStatus;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object[] getDataWS(Object object) throws Exception {
		Object[] result = new Object[2];
		
		ResponseDto responseDto = null;
		HttpStatus httpStatus = null;
		
		String jsonString = "";
		
		try {
			
			if (object instanceof List<?>) {
				if (((List<?>) object).size() > 0 && object != null) {
					httpStatus = HttpStatus.OK;
					jsonString = WSUtils.jsonStringConversion(object);
					responseDto = WSUtils.getRespondAndDetail("", true, httpStatus.toString(), jsonString);
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					responseDto = WSUtils.getRespondAndDetail("DATA NOT FOUND", false, httpStatus.toString(), jsonString);
				}
			} else {
				if (object != null) {
					httpStatus = HttpStatus.OK;
					jsonString = WSUtils.jsonStringConversion(object);
					responseDto = WSUtils.getRespondAndDetail("", true, httpStatus.toString(), jsonString);
				} else {
					httpStatus = HttpStatus.NOT_FOUND;
					responseDto = WSUtils.getRespondAndDetail("DATA NOT FOUND", false, httpStatus.toString(), jsonString);
				}
			}
			
			result[0] = responseDto;
			result[1] = httpStatus;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public String methodGET(String uri) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			result = br.readLine();
			
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public String methodPOST(String uri, String jsonData) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonData.getBytes());
			os.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			result = br.readLine();
			
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public List<Object> methodGETAll(String uri, Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String retrievedData = br.readLine();
			
			conn.disconnect();
			
			result = WSUtils.fromJsonArray(retrievedData, clazz);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object methodGETSingle(String uri, Class<?> clazz) throws Exception {
		// TODO Auto-generated method stub
		Object result = clazz.newInstance();
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String retrievedData = br.readLine();
			
			conn.disconnect();
			
			result = WSUtils.fromJson(retrievedData, clazz);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public Object methodPOST(String uri, Object dataDto, Class<?> respondDtoClazz) throws Exception {
		// TODO Auto-generated method stub
		Object result = respondDtoClazz.newInstance();
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String jsonData = WSUtils.toJson(dataDto);
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonData.getBytes());
			os.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String retrievedData = br.readLine();
			
			conn.disconnect();
			
			result = WSUtils.fromJson(retrievedData, respondDtoClazz);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public String methodPOST(String uri, Object dto) throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		
		try {
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			String jsonData = WSUtils.toJson(dto);
			
			OutputStream os = conn.getOutputStream();
			os.write(jsonData.getBytes());
			os.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			result = br.readLine();
			
			conn.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	 
}
