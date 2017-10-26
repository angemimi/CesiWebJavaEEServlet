package com.angemimi.storage;

public class StorageSession {
	public static String sessionId;
	
	public static boolean sessionAccess(String cookieValue){
		if(sessionId.equals(cookieValue)) {
			return true;
		}
		return false;
	}
}
