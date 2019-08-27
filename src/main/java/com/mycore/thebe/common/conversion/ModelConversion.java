package com.mycore.thebe.common.conversion;

/**
 * Class to convert Object to DTO and vice versa
 * @author Thebe.Alfarisi
 * @since Dec, 12th 2018
 * @version 1.0
 *
 */
public class ModelConversion extends DTOConversion {
	
	/**
	 * Method to map and convert data from {@link Object} entity to {@link Object} model
	 * @param dto target {@link Object} model
	 * @param entity source {@link Object} entity
	 * @return {@link Object} dto result
	 * @throws Exception
	 */
	public static Object toModel(Object model, Object entity) throws Exception {
		Object result = null;
		
		try {
			result = toDto(model, entity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Method to map and convert data from {@link Object} model to {@link Object} entity
	 * @param dto source {@link Object} model
	 * @param entity target {@link Object} entity
	 * @return {@link Object} entity result
	 * @throws Exception
	 */
	public static Object toEntity(Object model, Object entity) throws Exception {
		Object result = null;
		
		try {
			result = fromDto(model, entity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
