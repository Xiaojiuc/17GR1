package com.qhit.user.bean;
/**
 * 用户实体类
 * @author Administrator
 *
 */
public class User {
	private String username;
	private String password;
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
