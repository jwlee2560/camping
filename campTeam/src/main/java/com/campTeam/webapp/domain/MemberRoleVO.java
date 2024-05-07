package com.campTeam.webapp.domain;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MemberRoleVO extends MemberVO {

	private String role;
	
	private int page;

	@Override
	public String toString() {
		return "MemberRoleVO [role=" + role + ", page=" + page + ", toString()=" + super.toString() + "]";
	}
	
}
