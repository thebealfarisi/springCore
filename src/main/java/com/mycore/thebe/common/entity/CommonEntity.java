package com.mycore.thebe.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.mycore.thebe.common.conversion.ModelConversion;

/**
 * Common class that stores common entity parameter
 * @author Thebe.Alfarisi
 * @since Nov, 21st 2018
 * @version 1.0
 *
 */
@MappedSuperclass
public class CommonEntity {
	
	@Column(name="created_time")
	private Timestamp createdTime;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="modified_time")
	private Timestamp modifiedTime;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@Column(name="deleted_status")
	private Integer deletedStatus;
	
	public Integer getDeletedStatus() {
		return deletedStatus;
	}

	public void setDeletedStatus(Integer deletedStatus) {
		this.deletedStatus = deletedStatus;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	//CONVERSION01 USING FIELD (SAVED FOR LATER)
	public void toEntity(Object model) {
		if (model != null) {
			try {
				ModelConversion.toEntity(model, this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public Object toModel(Class clazz) {
		Object result = null;
		try {
			result = clazz.newInstance();
			ModelConversion.toModel(result, this);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}	
	
	//CONVERSION02 USING BEANUTILS
//	public void toEntity(Object model) {
//		if (model != null) {
//			BeanUtils.copyProperties(model, this);
//		}
//	}
//	
//	@SuppressWarnings("rawtypes")
//	public Object toModel(Class clazz) {
//		Object result = null;
//		try {
//			result = clazz.newInstance();
//			BeanUtils.copyProperties(this, result);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	//CONVERSION02 USING PROPERTYUTILSBEAND (SAVED FOR LATER)
//	public void toEntity(Object model) throws Exception {
//		if (model != null) {
//			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
//			propertyUtilsBean.copyProperties(this, model);
//		}
//	}
//		
//	@SuppressWarnings("rawtypes")
//	public Object toModel(Class clazz) throws Exception {
//		Object result = null;
//		try {
//			result = clazz.newInstance();
//			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
//			propertyUtilsBean.copyProperties(result, this);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}

	//CONVERSION02 USING MODEL MAPPER SKIP DULU (SAVED FOR LATER)
//	public void toEntity04(Object model) {
//		if (model != null) {
//			BeanUtils.copyProperties(model, this);
//		}
//	}
//		
//	@SuppressWarnings("rawtypes")
//	public Object toModel04(Class clazz) {
//		Object result = null;
//		try {
//			result = clazz.newInstance();
//			BeanUtils.copyProperties(this, result);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return result;
//	}
}
