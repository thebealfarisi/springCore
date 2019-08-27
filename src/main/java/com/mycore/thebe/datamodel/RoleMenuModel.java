package com.mycore.thebe.datamodel;

import java.io.Serializable;

import com.mycore.thebe.common.model.CommonModel;
import com.mycore.thebe.entity.RoleMenu;

/**
 * Class to handle {@link RoleMenu} form
 * @author Thebe.Alfarisi
 * @since Dec, 3rd 2018
 * @version 1.0
 *
 */
public class RoleMenuModel extends CommonModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String roleMenuId;
	private String menuName;
	private String menuLink;
	private String description;
	private String roleId;
	
	//CHECK IS NEW
	private boolean isNew = false;
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	//
	
	public String getRoleMenuId() {
		return roleMenuId;
	}
	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
