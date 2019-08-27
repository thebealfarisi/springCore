package com.mycore.thebe.common.service;

import java.io.Serializable;
import java.util.List;

import com.mycore.thebe.common.data.ActionUser;

/**
 * Common Service of all services
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public interface CommonService {
	
	/**
	 * Common service to create or update existing {@link Object}
	 * @param entity {@link Object} to be created or updated
	 * @return created or updated {@link Object} data
	 * @throws Exception
	 */
	public Object store(Object entity) throws Exception;
	
	/**
	 * Common service to create or update existing {@link Object}
	 * @param entity {@link Object} to be created or updated
	 * @param actionUser the {@link ActionUser} that do the process
	 * @return created or updated {@link Object} data
	 * @throws Exception
	 */
	public Object store(Object entity, ActionUser actionUser) throws Exception;
	
	/**
	 * Common service to delete new {@link Object}
	 * @param entity {@link Object} to be deleted
	 * @return deleted {@link Object} data
	 * @throws Exception
	 */
	public Object delete(Object entity) throws Exception;

	/**
	 * Common service to retrieve existing {@link Object}
	 * @param id {@link Object}'s id
	 * @param clazz {@link Object}'s class
	 * @return retrieved {@link Object} data
	 * @throws Exception
	 */
	public Object getData(Serializable id, Class<?> clazz) throws Exception;
	
	/**
	 * Common service to retrieve all {@link Object}s
	 * @param clazz {@link Object}'s class
	 * @return {@link List} of retrieved {@link Object}
	 * @throws Exception
	 */
	public List<Object> getAllData(Class<?> clazz) throws Exception;
	
	/**
	 * Common service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param filter filter criteria ('LIKE', 'EQUALS', 'BETWEEN')
	 * @param columns {@link Object} column(s)
	 * @param params01 {@link Object} first parameter(s)
	 * @param params02 {@link Object} second parameter(s)
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02) throws Exception;
	
	/**
	 * Common service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param filter filter criteria ('LIKE', 'EQUALS', 'BETWEEN')
	 * @param columns {@link Object} column(s)
	 * @param params01 {@link Object} first parameter(s)
	 * @param params02 {@link Object} second parameter(s)
	 * @param isAsc ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param sortColumns column to be ascended or descended
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02, boolean isAsc, String[] sortColumns) throws Exception;
	
	/**
	 * Common service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param filter filter criteria ('LIKE', 'EQUALS', 'BETWEEN')
	 * @param columns {@link Object} column(s)
	 * @param params01 {@link Object} first parameter(s)
	 * @param params02 {@link Object} second parameter(s)
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02, int initialRow, int maxData) throws Exception;
	
	/**
	 * Common service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param filter filter criteria ('LIKE', 'EQUALS', 'BETWEEN')
	 * @param columns {@link Object} column(s)
	 * @param params01 {@link Object} first parameter(s)
	 * @param params02 {@link Object} second parameter(s)
	 * @param isAsc ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param sortColumns column to be ascended or descended
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02, boolean isAsc, String[] sortColumns, int initialRow, int maxData) throws Exception;

	/**
	 * Common complex service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param likeCols like columns parameter
	 * @param likePars like values parameter
	 * @param eqCols equals columns parameter
	 * @param eqPars equals values parameter
	 * @param betweenCols between columns parameter
	 * @param between01Pars between lower values parameter
	 * @param between02Pars between higher values parameter
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars) throws Exception;
	
	/**
	 * Common complex service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param likeCols like columns parameter
	 * @param likePars like values parameter
	 * @param eqCols equals columns parameter
	 * @param eqPars equals values parameter
	 * @param betweenCols between columns parameter
	 * @param between01Pars between lower values parameter
	 * @param between02Pars between higher values parameter
	 * @param isAsc ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param sortColumns column to be ascended or descended
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, boolean isAsc, String[] sortColumns) throws Exception;
	
	/**
	 * Common complex service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param likeCols like columns parameter
	 * @param likePars like values parameter
	 * @param eqCols equals columns parameter
	 * @param eqPars equals values parameter
	 * @param betweenCols between columns parameter
	 * @param between01Pars between lower values parameter
	 * @param between02Pars between higher values parameter
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, int initialRow, int maxData) throws Exception;
	
	/**
	 * Common complex service to retrieve {@link Object} data based on given parameter(s)
	 * @param clazz {@link Object}'s class
	 * @param likeCols like columns parameter
	 * @param likePars like values parameter
	 * @param eqCols equals columns parameter
	 * @param eqPars equals values parameter
	 * @param betweenCols between columns parameter
	 * @param between01Pars between lower values parameter
	 * @param between02Pars between higher values parameter
	 * @param isAsc ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param sortColumns column to be ascended or descended
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return {@link List} of {@link Object} based on given parameter(s)
	 * @throws Exception
	 */
	public List<Object> searchDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars, boolean isAsc, String[] sortColumns, int initialRow, int maxData) throws Exception;
	
	/**
	 * Common service to get total {@link Object} data based on given parameter(s) 
	 * @param clazz {@link Object}'s class
	 * @param filter filter criteria ('LIKE', 'EQUALS', 'BETWEEN')
	 * @param columns {@link Object} column(s)
	 * @param params01 {@link Object} first parameter(s)
	 * @param params02 {@link Object} second parameter(s)
	 * @return total {@link Object} data
	 * @throws Exception
	 */
	public int totalData(Class<?> clazz, String filter, String[] columns, Object[] params01, Object[] params02) throws Exception;
	
	/**
	 * Common complex service to get total {@link Object} data based on given parameter(s) 
	 * @param clazz {@link Object}'s class
	 * @param likeCols like columns parameter
	 * @param likePars like values parameter
	 * @param eqCols equals columns parameter
	 * @param eqPars equals values parameter
	 * @param betweenCols between columns parameter
	 * @param between01Pars between lower values parameter
	 * @param between02Pars between higher values parameter
	 * @return total {@link Object} data
	 * @throws Exception
	 */
	public int totalDataC(Class<?> clazz, String[] likeCols, Object[] likePars, String[] eqCols, Object[] eqPars, String[] betweenCols, Object[] between01Pars, Object[] between02Pars) throws Exception;
	
	/**
	 * Common service to get unique {@link Object} based on given parameter(s) and 'EQUALS' criteria
	 * @param clazz {@link Object}'s class
	 * @param columns {@link Object} column(s)
	 * @param params {@link Object} parameter(s)
	 * @return unique result of {@link Object} based on given parameter(s) with 'EQUALS' criteria'
	 * @throws Exception
	 */
	public Object getUnique(Class<?> clazz, String[] columns, Object[] params) throws Exception;
	
	/**
	 * Common service to get single data {@link Object} in ponds of data based on given parameter(s) and 'EQUALS' criteria
	 * @param clazz {@link Object}'s class
	 * @param columns {@link Object} column(s)
	 * @param params {@link Object} parameter(s)
	 * @return unique result of {@link Object} based on given parameter(s) with 'EQUALS' criteria'
	 * @throws Exception
	 */
	public Object getSingleData(Class<?> clazz, String[] columns, Object[] params) throws Exception;
	
	/**
	 * Common dao to retrieve {@link Object}s
	 * @param procedure {@link String} procedure's name
	 * @param inputParameter Array of {@link Object} of procedure input parameter
	 * @param clazz {@link Object}'s class
	 * @return {@link List} of retrieved {@link Object}s
	 * @throws Exception
	 */
	public List<Object> getEntityBySP(String procedure, Object[] inputParameter, Class<?> clazz) throws Exception;
	
}
