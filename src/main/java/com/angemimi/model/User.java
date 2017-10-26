package com.angemimi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
	private String username;
	private String pwd;
	
	public User(String u, String pwd){
		this.username = u;
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public static final Gson gson = new Gson();
	
	public String toJsonDetail() {
		return gson.toJson(this);		
	}
	
	public static User fromJson(String json){
		return gson.fromJson(json, User.class);
	}
	
	public String toJSON() {
		final GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();
	    final Gson gsonShort = builder.create();
		
		return gsonShort.toJson(this);
	}
	
	public boolean isValid(String username, String pwd){
		if((this.username.equals(username)) && (this.pwd.equals(pwd))){
			return true;
		}
		return false;
	}
}
