/**
 * 
 */
package com.mycore.thebe.datamodel;

import java.io.Serializable;

import com.mycore.thebe.common.model.CommonModel;
import com.mycore.thebe.entity.Role;

/**
 * Class to handle {@link Role} form
 * @author Thebe.Alfarisi
 * @since Nov, 23rd 2018
 * @version 1.0
 *
 */
public class RoleModel extends CommonModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleId;
	private String roleName;
	private String description;
	private String mainPage;
	private String mainMenu;
	
	//CHECK IS NEW
	private boolean isNew = false;
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	//
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}
	
}
