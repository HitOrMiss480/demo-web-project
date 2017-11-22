package edu.csupomona.cs480.data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Events {
	@JsonProperty("EventId")
	private String EventId;
	@JsonProperty("EventName")
	private String EventName;
	@JsonProperty("Org")
	private String Org;
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

	public String getOrg() {
		return Org;
	}
	public void setOrg(String org) {
		Org = org;
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
	
}
