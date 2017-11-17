package edu.csupomona.cs480.data;

import java.util.ArrayList;
import java.util.Date;


/**
 * The basic user object.
 */
public class User {

	/** The unique user Id */
    private String id;
    /** The unique user Id */
    private String name;
    /** The unique user Id */
    private String userName;

    private ArrayList<Events> UserEvents = new ArrayList<Events>();
    
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
