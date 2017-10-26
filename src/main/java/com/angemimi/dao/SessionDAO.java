package com.angemimi.dao;

import org.bson.Document;

import com.angemimi.model.Session;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class SessionDAO {
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> sessions;
	private Gson gson;
	
	private SessionDAO(){
		gson = new Gson();
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("cookies");
		sessions = database.getCollection("users");
	}
	
	public static SessionDAO instance;
	
	public static SessionDAO getInstance(){
		if (null != instance) {
			return instance;
		}
		instance = new SessionDAO();
		return instance;
	}
	
	public Session getSession(String name){
		Document sessionDoc = sessions.find(Filters.eq("name", name)).first();
		
		if (null == sessionDoc){
			return null;
		}
		
		Session sess = gson.fromJson(sessionDoc.toJson(), Session.class);
		
		return sess;
	}
	
	public Document generateDocSession(Session sess) {
		Document doc = new Document()
				.append("name", sess.getName())
				.append("val", sess.getVal());
		return doc;
	}
	
	public void create(Session sess){
		sessions.insertOne(generateDocSession(sess));
	}
}
