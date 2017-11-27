package edu.csupomona.cs480.data;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * The basic user object.
 */
public class User {

	/** The unique user Id */
	@JsonProperty("UserId")
    private String id;
    /** The unique user Id */
	@JsonProperty("Name")
    private String name;
    /** The unique user Id */
	@JsonProperty("UserName")
    private String UserName;
    /** The timestamp when the user is being created */
   

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
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
}
