package reports;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import database.MongoConnector;
import entities.Incident;
import entities.IncidentInfos;
import enums.Stations;
import twitter.TwitterConnector;
import validators.LineValidator;
import validators.TimeValidator;

@Path("/trains")
@Service
public class ReportsActions {
	
	private LineValidator lineValidator = new LineValidator();
	
	private TimeValidator timeValidator = new TimeValidator();
	
	private MongoDatabase database = MongoConnector.getMongoClient().getDatabase("TRAINS_INCIDENTS");
	
	@Autowired
	private TwitterConnector twitterConnector;
	
	private Gson jsonTransfo = new Gson();
	
	@Path("/test")
	@GET
	public Response test(){
		return Response.status(200).entity("ok").build();
	}
	
	@Path("/balancer")
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.TEXT_PLAIN)
	public Response balanceTonTrain(InputReportsActions input){
		
		//Valider date et passer à un objet json par jour
		if(timeValidator.isValidTime(input.getIncidentDate()) && lineValidator.isValidData(input.getLine(), input.getStation())){
			
			//Tweet de l'incident
			Boolean isPosted=Boolean.TRUE;
			if(twitterConnector.postUpdate(Stations.valueOf(input.getStation()).getName(), input.getLine(), input.getIncidentDate()) != 0){
				isPosted=Boolean.FALSE;
			}
			
			//Mise à jour ou ajout dans la base mongo
			MongoCollection<Document> collection = database.getCollection("lines");
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("line", input.getLine());
			whereQuery.put("station", Stations.valueOf(input.getStation()).getName());
			BasicDBObject updateQuery = new BasicDBObject();
			BasicDBObject toPush = new BasicDBObject("infos",Document.parse(jsonTransfo.toJson(new IncidentInfos(input.getIncidentDate(), isPosted))));
			updateQuery.put("$push", toPush);

			if(collection.updateOne(whereQuery, updateQuery).getModifiedCount() < 1L){
				String testJson = jsonTransfo.toJson(new Incident(input.getLine(), Stations.valueOf(input.getStation()).getName(), input.getIncidentDate(), isPosted));
				collection.insertOne(Document.parse(testJson));
			}
		}
		return Response.status(200).entity("Your incident is being processed, thank you for reporting.").build();
	}
	
}