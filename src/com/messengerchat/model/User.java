package com.messengerchat.model;

public class User {
	private String username;
	private String password;
	private String name;
	
	public User(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public User() {
		this("nousername","nopassword","noname");
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "name : " + this.name + "\n" + "username : " + this.username + "\n";
	}
	
}
