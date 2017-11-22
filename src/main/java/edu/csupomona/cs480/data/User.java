package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * The basic user object.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	/** The unique user Id */
	@JsonProperty("id")
    private String id;
    /** The unique user Id */
	@JsonProperty("name")
    private String name;
    /** The unique user Id */
	@JsonProperty("userName")
    private String userName;

	@JsonProperty("UserEvents")
    private ArrayList<Events> UserEvents = new ArrayList<Events>();
    
	@JsonProperty("UserOrg")
    private ArrayList<Organizations> UserOrg = new ArrayList<Organizations>();
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void addEvent(Events e) {
		this.UserEvents.add(e);
	}
	
	public void addOrg(Organizations o) {
		this.UserOrg.add(o);
	}
}
