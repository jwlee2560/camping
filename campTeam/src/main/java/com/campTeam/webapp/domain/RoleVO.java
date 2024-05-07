package com.campTeam.webapp.domain;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="user_roles")
public class RoleVO implements GrantedAuthority {

	private static final long serialVersionUID = 7464267597005842862L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		    generator = "USER_ROLES_SEQ_GENERATOR")
	@SequenceGenerator(
			name = "USER_ROLES_SEQ_GENERATOR",
			sequenceName = "USER_ROLES_SEQ",
			initialValue = 1,
			allocationSize = 1)
	@Column(name = "user_role_id")
	private int id;
	
	@Column
	private String username;
	
	@Column
	private String role;

	// 추가
	public RoleVO() {}
	
	// 추가
	public RoleVO(String username, String role) {
		this.username = username;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return this.role;
	}

	@Override
	public String toString() {
		return "Role [username=" + username + ", role=" + role + "]";
	}
	
}