package com.angemimi.dao;

import org.bson.Document;

import com.angemimi.model.User;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserDAO {
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document>users;
	private Gson gson;

	private UserDAO(){
		gson = new Gson();
		mongoClient = new MongoClient();		
		database = mongoClient.getDatabase("cookies");
		users = database.getCollection("users");
	}
	
	private static UserDAO instance;
	
	public static UserDAO getInstance(){
		if (null != instance) {
			return instance;
		}
		instance = new UserDAO();
		return instance;
	}
	
	public User getUser(String username){
		Document userDocument = users.find(Filters.eq("username", username)).first();
		
		if (null == userDocument) {
			return null;
		}
		
		User user = gson.fromJson(userDocument.toJson(), User.class);
		
		
		return user;
	}
	
	
}
