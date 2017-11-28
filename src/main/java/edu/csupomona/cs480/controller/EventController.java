package edu.csupomona.cs480.controller;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.*;

import edu.csupomona.cs480.data.Events;
import edu.csupomona.cs480.data.OrgWrapper;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.App;
import edu.csupomona.cs480.DAL.DataAccess;
import edu.csupomona.cs480.constants.*;

@RestController
public class EventController {

	private final static Log logger = LogFactory.getLog(App.class);
	private static final String APPLICATION_NAME = "";
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static com.google.api.services.calendar.Calendar client;

	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	Credential credential;

	//@Value("${google.client.client-id}")
	private String clientId = "1009700773461-fc1leqha280auu2duur3f2edoluteo0v.apps.googleusercontent.com";
	//@Value("${google.client.client-secret}")
	private String clientSecret = "ePVBJ3JAWx10yMXT7mFuV-0S";
	//@Value("${google.client.redirectUri}")
	private String redirectURI = "http://localhost:8080/login/google";
	
	
	public Gson gson = new GsonBuilder().create();
	
	@Autowired
	private DataAccess eventManager;
	
	@RequestMapping(value = "/events/{userId}",method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
		try {
			ArrayList<Events> events = eventManager.getEvents();
			ArrayList<Events> userEvents = eventManager.getUserEvents(userId);
			
			if(events.isEmpty()|| events == null) {
				ErrorPackage error = new ErrorPackage(HttpStatus.NOT_FOUND.value(),Constants.EventNotFound);
				return new ResponseEntity<>(gson.toJson(error),HttpStatus.NOT_FOUND);
			}
			for(Events e : events) {
				for(Events ue : userEvents) {
					if(e.getEventId() == ue.getEventId()) {
						e.setPlanner("True");
					}
					else {
						e.setPlanner("False");
					}
				}
			}
			
			return new ResponseEntity<>(gson.toJson(events),HttpStatus.OK);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>("!!!!add json string here!!!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value = "/event/{userId}", method = RequestMethod.POST, produces = "application/json")
	ResponseEntity<?> createUser(@RequestBody ArrayList<Events> events, @PathVariable("userId") String userId){
		try {
			// add post code
			//ArrayList<ErrorPackage> errors = validateUser(user);
			//if(!errors.isEmpty()) {
				//return new ResponseEntity<>(gson.toJson(errors),HttpStatus.BAD_REQUEST);
			//}
			// database service call here
			//user.setId(UUID.randomUUID().toString());
			events = eventManager.addUserEvents(events, userId);
			// failed creation
			if(events == null || events.isEmpty()) {
				return new ResponseEntity<>(gson.toJson(new ErrorPackage(HttpStatus.BAD_REQUEST.value(), Constants.FailedCreateEvents)),HttpStatus.CREATED);
			}
				
			return new ResponseEntity<>(gson.toJson(events),HttpStatus.CREATED);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>("!!!!add json string here!!!!!",HttpStatus.INTERNAL_SERVER_ERROR);		
		}
	}
	
	@RequestMapping(value = "/events/org/userId={userId}",method = RequestMethod.POST,produces = "application/json")
	ResponseEntity<?> getUserEventsByOrg(@RequestBody String jsonString, @PathVariable("userId") String userId) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			OrgWrapper Orgs = mapper.readValue(jsonString, OrgWrapper.class);
			User user = eventManager.getUser(userId);
			ArrayList<Events> events = eventManager.GetUserEventsByOrg(Orgs.getIds(), userId);
			// add google call here
			createGoogleEvent(events,user.getName());
			
			eventManager.addUserEvents(events, userId);
			
			if(events.isEmpty()|| events == null) {
				ErrorPackage error = new ErrorPackage(HttpStatus.NOT_FOUND.value(),Constants.EventNotFound);
				return new ResponseEntity<>(gson.toJson(error),HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(gson.toJson(events),HttpStatus.OK);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	void createGoogleEvent(ArrayList<Events> events, String token) throws IOException, GeneralSecurityException {
		GoogleCredential credential = new GoogleCredential().setAccessToken(token);
		//credentialG = flow.createAndStoreCredential(response, "userID");
		httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		client = new com.google.api.services.calendar.Calendar.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
		for(Events e : events) {
			Event event = new Event()
				    .setSummary(e.getEventName())
				    .setLocation("test")
				    .setDescription("test")
				    .setColorId("5");
			DateTime startDateTime = new DateTime(e.getDate());
			EventDateTime start = new EventDateTime()
			    .setDateTime(startDateTime)
			    .setTimeZone("America/Los_Angeles");
			event.setStart(start);
			
			DateTime endDateTime = new DateTime(e.getDate());
			EventDateTime end = new EventDateTime()
			    .setDateTime(endDateTime)
			    .setTimeZone("America/Los_Angeles");
			event.setEnd(end);
			event = client.events().insert("primary", event).execute();	
		}
	}
}


