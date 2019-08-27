package com.mycore.thebe.process.service;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;

/**
 * Service Interface that control {@link User} registration process
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
public interface RegistrationService {

	/**
	 * Method to create new {@link User} or update existing {@link User} via {@link UserModel}
	 * @param model {@link UserModel} to be create or update then converted to {@link User}
	 * @param actionUser the {@link ActionUser} that save or update existing {@link User} via {@link UserModel}
	 * @return addition or updated {@link User} then converted to {@link User}
	 * @throws Exception
	 */
	public UserModel storeUser(UserModel model, ActionUser actionUser) throws Exception;

	/**
	 * Method to create new {@link User} or update existing {@link User} via {@link UserModel} with upload file
	 * @param model {@link UserModel} to be create or update then converted to {@link User}
	 * @param file {@link MultipartFile} file to be upload
	 * @param actionUser the {@link ActionUser} that save or update existing {@link User} via {@link UserModel}
	 * @param request {@link HttpServletRequest} to get the servlet data
	 * @return addition or updated {@link User} then converted to {@link User}
	 * @throws Exception
	 */
	public UserModel storeUser(UserModel model, MultipartFile file, ActionUser actionUser, HttpServletRequest request) throws Exception;
	
	/**
	 * Method to delete existing {@link User} via {@link UserModel}
	 * @param model {@link UserModel} to be deleted then converted to {@link User}
	 * @param actionUser the {@link ActionUser} that save or update existing {@link User} via {@link UserModel}
	 * @return deleted {@link User} then converted to {@link UserModel}
	 * @throws Exception
	 */
	public UserModel deleteUser(UserModel model, ActionUser actionUser) throws Exception;
	
	/**
	 * Method to retrieve {@link User} data by it's id
	 * @param id {@link UserModel}'s id to be retrieved
	 * @return retrieved {@link User} get by it's id 
	 * @throws Exception
	 */
	public User getUserById(Serializable id) throws Exception;
	
	/**
	 * Method to retrieve {@link List} of {@link User}
	 * @return {@link List} of {@link User}
	 */
	public List<User> getAllUsers();
	
	/**
	 * Method to retrieve {@link List} of active {@link User}
	 * @return {@link List} of {@link User}
	 */
	public List<User> getAllActiveUsers();
	
	/**
	 * Method to retrieve {@link List} of inactive {@link User}
	 * @return {@link List} of {@link User}
	 */
	public List<User> getAllInActiveUsers();
	
	/**
	 * Method to retrieve {@link User} data based on given filter
	 * @param likeColumns like columns parameter
	 * @param likeValues like values parameter
	 * @param eqColumns equals columns parameter
	 * @param eqValues equals values parameter
	 * @param betweenColumns between columns parameter
	 * @param betweenValues01 between lower values parameter
	 * @param betweenValues02 between higher values parameter
	 * @param inColumns in columns parameter
	 * @param inValues in values parameter
	 * @param notInColumns not in columns parameter
	 * @param notInValues not in values parameter
	 * @param isAscending ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param ascColumns column to be ascended or descended
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return list of {@link UserModel} based on given filter from {@link User}
	 * @throws Exception
	 */
	public List<User> search(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues, 
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02, String[] inColumns, 
			Object[] inValues, String[] notInColumns, Object[] notInValues, boolean isAscending, 
			String[] ascColumns, int initialRow, int maxData) throws Exception;
	
	/**
	 * Method to retrieve {@link User} data based on given filter
	 * @param likeColumns like columns parameter
	 * @param likeValues like values parameter
	 * @param eqColumns equals columns parameter
	 * @param eqValues equals values parameter
	 * @param betweenColumns between columns parameter
	 * @param betweenValues01 between lower values parameter
	 * @param betweenValues02 between higher values parameter
	 * @param isAscending ascending setter, <code>true</code> for ascending and <code>false</code> for descending
	 * @param ascColumns column to be ascended or descended
	 * @param initialRow initial row data value
	 * @param maxData maximum row to be retrieved
	 * @return list of {@link UserModel} based on given filter from {@link User}
	 * @throws Exception
	 */
	public List<User> search(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues, 
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02, boolean isAscending, 
			String[] ascColumns, int initialRow, int maxData) throws Exception;
	
	/**
	 * Method to get total {@link User} data based on given filter
	 * @param likeColumns like columns parameter
	 * @param likeValues like values parameter
	 * @param eqColumns equals columns parameter
	 * @param eqValues equals values parameter
	 * @param betweenColumns between columns parameter
	 * @param betweenValues01 between lower values parameter
	 * @param betweenValues02 between higher values parameter
	 * @return {@link User} total data based on given filter
	 * @throws Exception
	 */
	public int getTotalData(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues, 
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02) throws Exception;
	
	/**
	 * Method to check whether username already used or not
	 * @param username data to be checked
	 * @return <code>true</code> if exist, <code>false</code> if doesn't exist
	 * @throws Exception
	 */
	public boolean isUsernameExist(String username) throws Exception;
	
	/**
	 * Method to check whether username already used or not
	 * @param username data to be checked
	 * @return {@link User} data
	 * @throws Exception
	 */
	public User getUserByUserName(String username) throws Exception;
	
	/**
	 * Method to get all {@link Role} data
	 * @return {@link List} of {@link Role}
	 * @throws Exception
	 */
	public List<Role> getAllRoleData() throws Exception;
	
	/**
	 * Method to check total User data based on username parameter 
	 * @param username data to be checked
	 * @return <code>true</code> if return value = 1, <code>false</code> if return value > 1
	 * @throws Exception
	 */
	public boolean checkUserName(String username) throws Exception;
	
}
