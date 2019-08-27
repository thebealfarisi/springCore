package com.mycore.thebe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mycore.thebe.common.entity.CommonEntity;
import com.mycore.thebe.datamodel.RoleModel;

/**
 * Role entity that associate with tbl_role
 * @author Thebe.Alfarisi
 * @since Nov, 6th 2018
 * @version 1.0
 *
 */
@Entity
//@Proxy(lazy=false)
@Table(name="tbl_role")
public class Role extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer roleId;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="main_page")
	private String mainPage;
	
	@Column(name="main_menu")
	private String mainMenu;
	
	public Role() {
		
	}
	
	public Role(RoleModel model) throws Exception {
		this.toEntity(model);
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
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
