package com.mycore.thebe.process.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mycore.thebe.common.data.ActionUser;
import com.mycore.thebe.common.hibernate.HibernateAssistant;
import com.mycore.thebe.dao.UserDao;
import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.Role;
import com.mycore.thebe.entity.User;
import com.mycore.thebe.entity.service.RoleService;
import com.mycore.thebe.entity.service.UserService;
import com.mycore.thebe.process.service.RegistrationService;

/**
 * Implementation of {@link RegistrationService}
 * 
 * @author Thebe.Alfarisi
 * @since June, 28th 2018
 * @version 1.0
 *
 */
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public UserModel storeUser(UserModel model, ActionUser actionUser) throws Exception {
		UserModel result = new UserModel();
		String userBy = "";
		try {
			if (actionUser != null) {
				User actUser = actionUser.getUser();

				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}

			if (model.getUserId() == null) {
				model.setCreatedTime((new Timestamp(System.currentTimeMillis())).toString());
				model.setCreatedBy(userBy);
				model.setDeletedStatus("0");
			} else {
				model.setModifiedTime((new Timestamp(System.currentTimeMillis())).toString());
				model.setModifiedBy(userBy);
			}

			result = userDao.storeByForm(model);

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
	public UserModel storeUser(UserModel model, MultipartFile file, ActionUser actionUser, HttpServletRequest request) throws Exception {
		UserModel result = new UserModel();
		String userBy = "";
		
		try {
			if (actionUser != null) {
				User actUser = actionUser.getUser();

				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}

			if (model.getUserId() == null) {
				model.setCreatedTime((new Timestamp(System.currentTimeMillis())).toString());
				model.setCreatedBy(userBy);
				model.setDeletedStatus("0");
			} else {
				model.setModifiedTime((new Timestamp(System.currentTimeMillis())).toString());
				model.setModifiedBy(userBy);
			}
			
			User entity = new User(model);
			
			if (!file.isEmpty()) {
				String dir = "";
				String fileName = "";
				
				byte[] bytes = file.getBytes();
				
				if (file.getContentType().equals("image/jpeg")) {
					dir = File.separator;
					File imageDir = new File(dir);
					if (!imageDir.exists()) {
						imageDir.mkdirs();
					}
					fileName = file.getOriginalFilename();
					File imageFile = new File(imageDir + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageFile));
					stream.write(bytes);
					stream.close();
					
				} else {
					dir = "/spring/upload";
					fileName = file.getOriginalFilename();
					
					File directory = new File(dir);
					if (!directory.exists()) {
						directory.mkdirs();
					}
					
					Path path = Paths.get(directory + File.separator + fileName);
					Files.write(path, bytes);
				}
				
				entity.setDocumentPath(dir);
				entity.setDocumentName(fileName);
			}
			
			entity = (User) userDao.storeByEntity(entity);
			
			result = (UserModel) entity.toModel(UserModel.class);
			
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
	public UserModel deleteUser(UserModel model, ActionUser actionUser) throws Exception {
		UserModel result = new UserModel();
		String userBy = "";
		try {
			if (actionUser != null) {
				User actUser = actionUser.getUser();

				if (actUser != null) {
					userBy = actUser.getFirstName() + " " + actUser.getLastName();
				}
			}

			model.setModifiedTime((new Timestamp(System.currentTimeMillis())).toString());
			model.setModifiedBy(userBy);
			model.setDeletedStatus("1");

			result = userDao.storeByForm(model);

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
	public User getUserById(Serializable id) throws Exception {
		return (User) userDao.getByEntity(id, User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllUsers() {
		List<User> result = new ArrayList<User>();
		try {
			result = sessionFactory.getCurrentSession().createCriteria(User.class)
					.addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName")).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllActiveUsers() {
		List<User> result = new ArrayList<User>();
		try {
			Criterion deletedStatus = Restrictions.eq("deletedStatus", Integer.valueOf(0));

			result = sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(deletedStatus).addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName")).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> getAllInActiveUsers() {
		List<User> result = new ArrayList<User>();
		try {
			Criterion deletedStatus = Restrictions.eq("deletedStatus", Integer.valueOf(1));

			result = sessionFactory.getCurrentSession().createCriteria(User.class)
					.add(deletedStatus).addOrder(Order.asc("firstName")).addOrder(Order.asc("lastName")).list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> search(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues,
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02, String[] inColumns,
			Object[] inValues, String[] notInColumns, Object[] notInValues, boolean isAscending,
			String[] ascColumns, int initialRow, int maxData) throws Exception {
		List<User> result = new ArrayList<User>();

		try {
			Criteria userCriteria = sessionFactory.getCurrentSession().createCriteria(User.class);

			HibernateAssistant.setLikes(likeColumns, likeValues, userCriteria);
			HibernateAssistant.setEquals(eqColumns, eqValues, userCriteria);
			HibernateAssistant.setBetween(betweenColumns, betweenValues01, betweenValues02, userCriteria);
			HibernateAssistant.setIn(inColumns, inValues, userCriteria);
			HibernateAssistant.setNotIn(notInColumns, notInValues, userCriteria);
			HibernateAssistant.setOrdering(isAscending, ascColumns, userCriteria);
			HibernateAssistant.setLimit(initialRow, maxData, userCriteria);

			result = userCriteria.list();

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
	public List<User> search(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues,
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02, boolean isAscending,
			String[] ascColumns, int initialRow, int maxData) throws Exception {
		List<User> result = new ArrayList<User>();

		try {
			List<Object> objectResult = userService.searchDataC(User.class, likeColumns, likeValues, eqColumns, eqValues, betweenColumns, betweenValues01, betweenValues02, isAscending, ascColumns, initialRow, maxData);

			for (Object object : objectResult) {
				result.add((User) object);
			}

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
	public int getTotalData(String[] likeColumns, Object[] likeValues, String[] eqColumns, Object[] eqValues, 
			String[] betweenColumns, Object[] betweenValues01, Object[] betweenValues02) throws Exception {
		return userService.totalDataC(User.class, likeColumns, likeValues, eqColumns, eqValues, betweenColumns, betweenValues01, betweenValues02);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public boolean isUsernameExist(String username) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;

		try {
			String[] columns = { "username", "deletedStatus" };
			Object[] params = { username, Integer.valueOf(0) };

			User user = (User) userService.getUnique(User.class, columns, params);

			if (user != null) {
				result = true;
			}

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
	public User getUserByUserName(String username) throws Exception {
		// TODO Auto-generated method stub
		User result = null;

		try {
			String[] columns = { "username", "deletedStatus" };
			Object[] params = { username, Integer.valueOf(0) };

			result = (User) userService.getSingleData(User.class, columns, params);

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
	public List<Role> getAllRoleData() throws Exception {
		List<Role> result = new ArrayList<Role>();
		
		try {
			String[] columns = {"deletedStatus"};
			Object[] params01 = {Integer.valueOf(0)};
			String[] sortColumns = {"roleId"};
			
			List<Object> objects = roleService.searchData(Role.class, "EQUALS", columns, params01, null, true, sortColumns);
			
			for (Object obj : objects) {
				result.add((Role) obj);
			}
			
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
	public boolean checkUserName(String username) throws Exception {
		boolean result = false;
		
		try {
			String[] eqCols = {"username", "deletedStatus"};
			Object[] eqPars = {username, Integer.valueOf(0)};
			
			int totalData = userService.totalData(User.class, "EQUALS", eqCols, eqPars, null);
			
			if (totalData == 1) {
				result = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

}
