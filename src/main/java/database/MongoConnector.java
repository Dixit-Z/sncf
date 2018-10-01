package database;

import com.mongodb.MongoClient;

public class MongoConnector {
	
	private MongoConnector(){
		//Private constructor
	}
 
	private static MongoClient mongoClient = new MongoClient("localhost", 27017);
	
	public static MongoClient getMongoClient(){
		return mongoClient;
	}
	
}
