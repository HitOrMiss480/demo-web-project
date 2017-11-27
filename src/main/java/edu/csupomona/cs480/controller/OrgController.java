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
public class OrgController {

	public Gson gson = new GsonBuilder().create();
	
	@Autowired
	private DataAccess orgManager;
	
	@RequestMapping(value = "/org/userId = {userId}",method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<?> getOrg(@PathVariable("userId") String userId) {
		try {
			ArrayList<Organizations> orgs = orgManager.GetOrgs();
			ArrayList<Organizations> userOrg = orgManager.GetUserOrgs(userId);
			
			for(Organizations o : orgs) {
				for(Organizations uo : userOrg) {
					if(o.getOrgId().equals(uo.getOrgId())) {
						o.setCheck(true);
					}
				}
			}
			
			if(orgs.isEmpty()|| orgs == null) {
				ErrorPackage error = new ErrorPackage(HttpStatus.NOT_FOUND.value(),Constants.OrgsNotFound);
				return new ResponseEntity<>(gson.toJson(error),HttpStatus.NOT_FOUND);
			}
						
			return new ResponseEntity<>(gson.toJson(orgs),HttpStatus.OK);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>("!!!!add json string here!!!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value = "/org/user",method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<?> getUserOrg(@PathVariable("userId") String userId){
		try {
			ArrayList<Organizations> orgs = orgManager.GetUserOrgs(userId);
		
			if(orgs.isEmpty()|| orgs == null) {
				ErrorPackage error = new ErrorPackage(HttpStatus.NOT_FOUND.value(),Constants.OrgsNotFound);
				return new ResponseEntity<>(gson.toJson(error),HttpStatus.NOT_FOUND);
			}
						
			return new ResponseEntity<>(gson.toJson(orgs),HttpStatus.OK);
		}
		catch(Exception e) {
			// !!!!! Set up error handler here!!!!
			return new ResponseEntity<>("!!!!add json string here!!!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
