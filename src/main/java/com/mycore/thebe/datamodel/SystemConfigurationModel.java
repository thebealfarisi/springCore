package com.mycore.thebe.datamodel;

import java.io.Serializable;

import com.mycore.thebe.common.model.CommonModel;
import com.mycore.thebe.entity.SystemConfiguration;

/**
 * Class to handle {@link SystemConfiguration} form
 * @author Thebe.Alfarisi
 * @since Dec, 27th 2018
 * @version 1.0
 *
 */
public class SystemConfigurationModel extends CommonModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String systemName;
	private String description;
	private String value01;
	private String value02;
	private String value03;
	private String value04;
	private String value05;
	private String value06;
	
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue01() {
		return value01;
	}
	public void setValue01(String value01) {
		this.value01 = value01;
	}
	public String getValue02() {
		return value02;
	}
	public void setValue02(String value02) {
		this.value02 = value02;
	}
	public String getValue03() {
		return value03;
	}
	public void setValue03(String value03) {
		this.value03 = value03;
	}
	public String getValue04() {
		return value04;
	}
	public void setValue04(String value04) {
		this.value04 = value04;
	}
	public String getValue05() {
		return value05;
	}
	public void setValue05(String value05) {
		this.value05 = value05;
	}
	public String getValue06() {
		return value06;
	}
	public void setValue06(String value06) {
		this.value06 = value06;
	}
	
}
