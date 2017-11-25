package edu.csupomona.cs480.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {
	@JsonProperty("EventId")
	private String EventId;
	@JsonProperty("EventName")
	private String EventName;
	@JsonProperty("OrgId")
	private String OrgId;
	@JsonProperty("OrgName")
	private String OrgName;
	@JsonProperty("Date")
	private String Date;
	@JsonProperty("inPlanner")
	private String inPlanner;
	
	public String getEventId() {
		return EventId;
	}
	public void setEventId(String eventId) {
		EventId = eventId;
	}
	public String getEventName() {
		return EventName;
	}
	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getOrgId() {
		return OrgId;
	}
	public void setOrgId(String org) {
		OrgId = org;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String inPlanner() {
		return inPlanner;
	}
	public void setPlanner(String inPlanner) {
		this.inPlanner = inPlanner;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	
}
