package com.campTeam.webapp.domain;

import lombok.Data;

@Data
public class Users {

	private String id;
	private String pw;
	private int enabled;

}