/**
 * 
 */
package com.mycore.thebe.testrun;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.dozer.DozerBeanMapper;

import com.mycore.thebe.datamodel.UserModel;
import com.mycore.thebe.entity.User;

/**
 * Class to compare conversion process using {@link BeanUtils}, {@link PropertyUtils}, and {@link DozerBeanMapper} 
 * @author Thebe.Alfarisi
 * @since Nov, 21st 2018
 * @version 1.0
 *
 */
public class ConversionTest {
	
	/**
	 * Main method to be executed first
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		UserModel userForm = new UserModel();
		User user = new User();
		
		user.setUserId(100);
		user.setFirstName("Aink");
		user.setLastName("Pisan");
		user.setCreatedBy("WKWKWKWK");
		
		System.out.println("---BEFORE---");
		System.out.println(user.getUserId());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getCreatedBy());
		
		userForm.setFirstName("Thebe");
		userForm.setLastName("Alfarisi");
		
		beanUtilsTest(user, userForm);
		
		System.out.println("---AFTER---");
		System.out.println(user.getUserId());
		System.out.println(user.getFirstName());
		System.out.println(user.getLastName());
		System.out.println(user.getCreatedBy());
	}
	
	private static void beanUtilsTest(Object dest, Object source) throws Exception {
		BeanUtils.copyProperties(dest, source);
	}
	
	@SuppressWarnings("unused")
	private static void propertyUtilsTest(Object dest, Object source) throws Exception {
		PropertyUtils.copyProperties(dest, source);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private static void dozerTest(Object dest, Object source, Class clazz) throws Exception {
		dest = new DozerBeanMapper().map(source, clazz);
	}

}
