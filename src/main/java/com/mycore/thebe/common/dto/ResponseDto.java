package com.mycore.thebe.common.dto;

/**
 * REST response data transfer object class
 * @author Thebe.Alfarisi
 * @since Dec, 20th 2018
 * @version 1.0
 *
 */
public class ResponseDto {

	private String status;
	private String decsription;
	private String statusCode;
	private String data;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDecsription() {
		return decsription;
	}
	public void setDecsription(String decsription) {
		this.decsription = decsription;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
