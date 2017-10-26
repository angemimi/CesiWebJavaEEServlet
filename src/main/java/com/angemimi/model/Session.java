package com.angemimi.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Session {
	private String name;
	private String val;
	
	public Session(String name, String val){
		this.name = name;
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	public static final Gson gson = new Gson();
	
	public String toJsonDetail() {
		return gson.toJson(this);		
	}
	
	public static Session fromJson(String json){
		return gson.fromJson(json, Session.class);
	}
	
	public String toJSON() {
		final GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();
	    final Gson gsonShort = builder.create();
		
		return gsonShort.toJson(this);
	}
	
	public boolean isValid(String val){
		if(this.val.equals(val)){
			return true;
		}
		return false;
	}
}
