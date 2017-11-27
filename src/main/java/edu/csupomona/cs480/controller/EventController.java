package edu.csupomona.cs480.controller;


import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.common.base.Splitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.type.TypeFactory;

import edu.csupomona.cs480.App;
import edu.csupomona.cs480.data.Events;
import edu.csupomona.cs480.data.OrgWrapper;
import edu.csupomona.cs480.data.Organizations;
import edu.csupomona.cs480.data.User;
import edu.csupomona.cs480.data.provider.UserManager;
import edu.csupomona.cs480.DAL.DataAccess;
import edu.csupomona.cs480.constants.*;

@RestController
public class EventController {

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
			
			ArrayList<Events> events = eventManager.GetUserEventsByOrg(Orgs.getIds(), userId);
			// add google call here
			eventManager.addUserEvents(events, userId);
			
			if(events.isEmpty()|| events == null) {
				ErrorPackage error = new ErrorPackage(HttpStatus.NOT_FOUND.value(),Constants.EventNotFound);
				return new ResponseEntity<>(gson.toJson(error),HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<>(gson.toJson(events),HttpStatus.OK);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>("!!!!add json string here!!!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
}


