package com.mycore.thebe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.mycore.thebe.common.entity.CommonEntity;
import com.mycore.thebe.datamodel.RoleMenuModel;

/**
 * User Menu entity that associate with table role_menu 
 * @author Thebe.Alfarisi
 * @since Nov, 7th 2018
 * @version 1.0
 *
 */
@Entity
@Table(name="role_menu")
public class RoleMenu extends CommonEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="role_menu_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer roleMenuId;
	
	@Column(name="menu_name")
	private String menuName;
	
	@Column(name="menu_link")
	private String menuLink;
	
	@Column(name="description")
	private String description;
	
	//FOREIGN KEY
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	@Fetch(FetchMode.JOIN)
	private Role roleId;
	
	public RoleMenu() {
		
	}
	
	public RoleMenu(RoleMenuModel model) throws Exception {
		this.toEntity(model);
	}
	
	public Integer getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Integer roleMenuId) {
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

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
	
}
