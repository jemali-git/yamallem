package com.atn.demo.mvc.module.user.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

import com.atn.commons.entities.ModelObject;

@Entity
@Table(name = "APP_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = "ROLE_ID"))
@Inheritance(strategy = InheritanceType.JOINED)
public class Role extends ModelObject<Integer> implements GrantedAuthority  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ROLE {

		WEB_USER("ROLE_WEB_USER"), ADMINISTRATOR("ROLE_ADMINISTRATOR");

		protected String value;

		ROLE(String value) {
			this.value = value;
		}
		@Override
		public String toString() {
			return value;
		}
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private Integer roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private ROLE role;

	@Override
	public Integer getId() {
		return getRoleId();
	}

	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public ROLE getRole() {
		return role;
	}


	public void setRole(ROLE role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role.value;
	}

}

