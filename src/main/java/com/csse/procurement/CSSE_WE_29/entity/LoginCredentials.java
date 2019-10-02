package com.csse.procurement.CSSE_WE_29.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loginCredentials")
public class LoginCredentials {
	@Id
	private String _id;
	private String username;
	private String password;
	private String userType;
	
	public LoginCredentials(String _id, String username, String password, String userType) {
		super();
		this._id = _id;
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public LoginCredentials(String username, String password, String userType) {
		super();
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public LoginCredentials(String username, String userType) {
		super();
		this.username = username;
		this.userType = userType;
	}

	public LoginCredentials() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
