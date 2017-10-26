package com.angemimi.storage;

import java.util.HashMap;
import java.util.Map;

public class StorageUsers {
	public static Map<String, String> users = new HashMap<String, String>();
	
	static {
        users.put("ange", "marie");
        users.put("dodo", "lenfentdo");
        users.put("cor", "nichon");
	}
	
	public static boolean isValid(String username, String pwd){
		if(users.get(username).equals(pwd)){
			return true;
		} 
		return false;
	}
}
