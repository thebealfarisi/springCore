package com.mycore.thebe.common.hibernate;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Assistant class to help retrieving data
 * @author Thebe.Alfarisi
 * @since July, 18th 2018
 * @version 1.0
 *
 */
public class HibernateAssistant {
	
	/**
	 * Method to create hibernate initialization
	 * @param foreignKeys list of foreign model
	 * @param model Object to be initialized
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void lazyInitialization(String[] foreignKeys, Object model) {
		Object dump = null;
		if (foreignKeys == null) {
			return;
		} else {
			for (int i = 0; i < foreignKeys.length; i++) {
				String[] foreignKey = foreignKeys[i].split("[.]");
				Object obj = model;
				Object tempObj = null;
				
				if (obj != null) {
					if (obj.getClass().getSimpleName().equals(foreignKey[0])) {
						for (int j = 1; j < foreignKey.length; j++) {
							try {
								if (obj instanceof Collection) {
									Collection collection = (Collection) obj;
									ArrayList arrayList = new ArrayList(collection.size());
									
									for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
										Object entity = (Object) iterator.next();
										
										Method method = entity.getClass().getMethod("get" + foreignKey[j], null);
										tempObj = method.invoke(entity, null);
										
										arrayList.add(tempObj);
									}
									obj = arrayList;
									
									if (obj == null) {
										break;
									}
								} else {
									Method method = obj.getClass().getMethod("get" + foreignKey[j], null);
									obj = method.invoke(obj, null);
									
									if (obj == null) {
										break;
									}
								}
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Method to get total row data base on parameter 'COUNT(*)'
	 * @param criteria Object to be retrieved
	 * @return total row data
	 */
	@SuppressWarnings("rawtypes")
	public static int getTotalData(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List list = criteria.list();

		if (list.size() > 0) {
			return ((Long) list.get(0)).intValue();
		} else {
			return 0;
		}
	}

	/**
	 * Method to add hibernate criteria base on parameter 'EQUAL'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setEquals(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					if (values[i] == null) {
						criteria.add(Restrictions.isNull(columns[i].substring(l+1)));
					} else {
						criteria.add(Restrictions.eq(columns[i].substring(l+1), values[i]));
					}
				} else {
					if (values[i] == null) {
						criteria.add(Restrictions.isNull(columns[i]));
					} else {
						criteria.add(Restrictions.eq(columns[i], values[i]));
					}
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'NOT EQUAL'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setNotEquals(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.ne(columns[i].substring(l+1), values[i]));
				} else {
					criteria.add(Restrictions.ne(columns[i], values[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'BETWEEN'
	 * @param columns list of parameter columns
	 * @param values01 list of lower values
	 * @param values02 list of higher values
	 * @param criteria Object to be retrieved
	 */
	public static void setBetween(String[] columns, Object[] values01, Object[] values02, Criteria criteria) {
		if ((columns == null || values01 == null || values02 == null) || (columns.length == 0 || values01.length == 0 || values02.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.between(columns[i].substring(l+1), values01[i], values02[i]));
				} else {
					criteria.add(Restrictions.between(columns[i], values01[i], values02[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'ILIKE' with match case 'EVERYWHERE'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setLikes(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					if (values[i] == null) {
						criteria.add(Restrictions.isNull(columns[i].substring(l+1)));
					} else {
						criteria.add(Restrictions.ilike(columns[i].substring(l+1), String.valueOf(values[i]), MatchMode.ANYWHERE));
					}
				} else {
					if (values[i] == null) {
						criteria.add(Restrictions.isNull(columns[i]));
					} else {
						criteria.add(Restrictions.ilike(columns[i], String.valueOf(values[i]), MatchMode.ANYWHERE));
					}
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria by ordering object's data based on ordering columns
	 * @param isAscending <code>true</code> for ascending and <code>false</code> for descending
	 * @param columns list of ordering columns
	 * @param criteria Object to be retrieved
	 */
	public static void setOrdering(boolean isAscending, String[] columns, Criteria criteria) {
		if (columns == null || columns.length == 0) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					if (isAscending) {
						criteria.addOrder(Order.asc(columns[i].substring(l+1)));
					} else {
						criteria.addOrder(Order.desc(columns[i].substring(l+1)));
					}
				} else {
					if (isAscending) {
						criteria.addOrder(Order.asc(columns[i]));
					} else {
						criteria.addOrder(Order.desc(columns[i]));
					}
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria to get total object's data based on initial row and limit
	 * @param index initial data row
	 * @param offset total retrieved data
	 * @param criteria Object to be retrieved
	 */
	public static void setLimit(int index, int offset, Criteria criteria) {
		if (index >= 0) {
			criteria.setFirstResult(index);
		}
		if (offset >= 0) {
			criteria.setMaxResults(offset);
		}
	}
	
	/**
	 * Method to get hibernate criteria base on parameter 'IN'
	 * @param columns list of parameter columns with same data type
	 * @param values list of parameter range value to be checked
	 * @param criteria Object to be retrieved
	 */
	public static void setIn(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.in(columns[i].substring(l+1), values));
				} else {
					criteria.add(Restrictions.in(columns[i], values));
				}
			}
		}
	}
	
	/**
	 * Method to get hibernate criteria base on parameter 'IN'
	 * @param columns list of parameter columns with same data type
	 * @param values list of parameter range value to be checked
	 * @param criteria Object to be retrieved
	 */
	@SuppressWarnings("rawtypes")
	public static void setIn(String[] columns, Collection values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.size() == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.in(columns[i].substring(l+1), values));
				} else {
					criteria.add(Restrictions.in(columns[i], values));
				}
			}
		}
	}
	
	/**
	 * Method to get hibernate criteria base on parameter 'NOT IN'
	 * @param columns list of parameter columns with same data type
	 * @param values list of parameter range value to be checked
	 * @param criteria Object to be retrieved
	 */
	public static void setNotIn(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.not(Restrictions.in(columns[i].substring(l+1), values)));
				} else {
					criteria.add(Restrictions.not(Restrictions.in(columns[i], values)));
				}
			}
		}
	}
	
	/**
	 * Method to get hibernate criteria base on parameter 'NOT IN'
	 * @param columns list of parameter columns with same data type
	 * @param values list of parameter range value to be checked
	 * @param criteria Object to be retrieved
	 */
	@SuppressWarnings("rawtypes")
	public static void setNotIn(String[] columns, Collection values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.size() == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.not(Restrictions.in(columns[i].substring(l+1), values)));
				} else {
					criteria.add(Restrictions.not(Restrictions.in(columns[i], values)));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'GREATER THAN'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setGreaterThan(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.gt(columns[i].substring(l+1), values[i]));
				} else {
					criteria.add(Restrictions.gt(columns[i], values[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'GREATER THAN EQUALS'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setGreaterThanEquals(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.ge(columns[i].substring(l+1), values[i]));
				} else {
					criteria.add(Restrictions.ge(columns[i], values[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'LESS THAN'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setLessThan(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.lt(columns[i].substring(l+1), values[i]));
				} else {
					criteria.add(Restrictions.lt(columns[i], values[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'LESS THAN EQUALS'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setLessThanEquals(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.le(columns[i].substring(l+1), values[i]));
				} else {
					criteria.add(Restrictions.le(columns[i], values[i]));
				}
			}
		}
	}
	
	/**
	 * Method to add hibernate criteria base on parameter 'IS NOT NULL'
	 * @param columns list of parameter columns 
	 * @param values list of parameter values
	 * @param criteria Object to be retrieved
	 */
	public static void setIsNotNull(String[] columns, Object[] values, Criteria criteria) {
		if ((columns == null || values == null) || (columns.length == 0 || values.length == 0)) {
			return;
		} else {
			for (int i = 0; i < columns.length; i++) {
				if (columns[i].indexOf(".") >= 0) {
					StringTokenizer stringToken = new StringTokenizer(columns[i], ".");
					int tokens = stringToken.countTokens();
					
					String foreign = "";
					String alias = "";
					String aliasChecker = "";
					
					for (int j = 0; j < tokens-1; j++) {
						String aliasData = stringToken.nextToken();
						
						if (j == 0) {
							foreign = aliasData;
						} else {
							foreign = alias + "." + aliasData;
						}
						
						aliasChecker = foreign.concat(":").concat(aliasData);
						
						if (criteria.toString().indexOf(aliasChecker) < 0) {
							criteria.createAlias(foreign, aliasData);
						}
						
						alias = aliasData;
					}
					
					int k = columns[i].lastIndexOf(".");
					int l = columns[i].lastIndexOf(".", k-1);
					
					criteria.add(Restrictions.isNotNull(columns[i].substring(l+1)));
				} else {
					criteria.add(Restrictions.isNotNull(columns[i]));
				}
			}
		}
	}
	
}
