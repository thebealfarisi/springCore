package com.mycore.thebe.common.hibernate;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to create sequence
 * @author Thebe.Alfarisi
 * @since Dec, 17th 2018
 * @version 1.0
 *
 */
public class SequenceCreator {

	@Autowired
	private static SessionFactory sessionFactory;
	
	/**
	 * Method to create sql sequence number 
	 * @param sequenceName name of the sequence
	 * @throws Exception
	 */
	public static void createSquence(String sequenceName) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("CREATE SEQUENCE " + sequenceName + " START WITH 1");
		query.executeUpdate();
	}
	
	/**
	 * Method to do the sequence
	 * @param sequenceName name of sequence
	 * @return sequence data value with {@link BigInteger} type
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static BigInteger doSequence(String sequenceName) throws Exception {
		BigInteger result = null;
		
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sequenceName);
		
		List<Object> list = query.list();
		if (list != null && !list.isEmpty()) {
			result = (BigInteger) list.iterator().next();
		}
		
		return result;
	}
	
}
