package edu.csupomona.cs480.data;

public class Events {
	private String EventId;
	private String EventName;
	private Organizations Org;
	private String Planner;
	private String Date;
	private boolean isPublic;
	
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
	public Organizations getOrg() {
		return Org;
	}
	public void setOrg(Organizations org) {
		Org = org;
	}
	public String getPlanner() {
		return Planner;
	}
	public void setPlanner(String planner) {
		Planner = planner;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
}
