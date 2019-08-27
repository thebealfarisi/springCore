package com.mycore.thebe.common.data;

/**
 * Class to help email attachment
 * @author Thebe.Alfarisi
 * @since Dec, 27th 2018
 * @version 1.0
 *
 */
public class EmailAssistant {

	private byte[] filebytes;
	private String fileType;
	private String fileName;
	
	public byte[] getFilebytes() {
		return filebytes;
	}
	public void setFilebytes(byte[] filebytes) {
		this.filebytes = filebytes;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
