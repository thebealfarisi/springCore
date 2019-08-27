package com.mycore.thebe.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Common Data Access Object for all daos
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public interface CommonDao {
	
	/**
	 * Common dao to create or update {@link Object} data
	 * @param entity {@link Object} to be created or updated
	 * @return created or updated {@link Object} data
	 * @throws Exception
	 */
	public Object storeByEntity(Object entity) throws Exception;
	
	/**
	 * Common dao to delete new {@link Object}
	 * @param entity {@link Object} to be deleted
	 * @return deleted {@link Object} data
	 * @throws Exception
	 */
	public Object flushByEntity(Object entity) throws Exception;

	/**
	 * Common dao to retrieve existing {@link Object}
	 * @param id {@link Object}'s id
	 * @param clazz {@link Object}'s class
	 * @return retrieved {@link Object} data
	 * @throws Exception
	 */
	public Object getByEntity(Serializable id, Class<?> clazz) throws Exception;
	
}
