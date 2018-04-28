package com.atn.demo.mvc.module.user.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.atn.commons.entities.ModelObject;

@Entity
@Table(name = "APP_USER", uniqueConstraints = @UniqueConstraint(columnNames = "USER_ID"))
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends ModelObject<Long> {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USER_ID", unique = true, nullable = false)
	private Long userId;

	@Size(max = 255)
	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Size(max = 255)
	@Column(name = "USER_PASSWORD")
	private String userPassword;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Role> roles;
	

	
	@Override
	public Long getId() {
		return getUserId();
	}

	public boolean hasRole(Role.ROLE role) {
		for (Role role2 : roles) {
			if (role2.getRole().equals(role)) {
				return true;
			}
		}
		return false;
	}
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
