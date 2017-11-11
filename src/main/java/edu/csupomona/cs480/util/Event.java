package edu.csupomona.cs480.util;

//event structured to follow google calendar API format
public class Event 
{
	//dateTime in timeZone format. reference here: https://tools.ietf.org/html/rfc3339
	// example: 2017-11-10T16:30:00-08:00
	private String startDateTime;
	private String endDateTime;
	//description of event in String format
	private String description;
	//Title of event 
	private String summaryTitle;
	//color of event displayed in google calendar
	// ranges from [1-11]
	private String colorId;
	//location of event
	private String location;
	//organizer's display name
	private String orgName;
	//organizer's email address (specific to each club)
	private String orgEmail;
	//kind of event
	private String kind = "calendar#event";
	//creators name
	private String creatorName;
	//creator's email
	private String creatorEmail;
	
	//constructor
	Event(String startTime, String endTime, String descript, String summary, String color,
			String address, String orgN, String orgE, String creatorN, String creatorE)
	{
		startDateTime = startTime;
		endDateTime = endTime;
		description = descript;
		summaryTitle = summary;
		colorId = color;
		location = address;
		orgName = orgN;
		orgEmail = orgE;
		creatorName = creatorN;
		creatorEmail = creatorE;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummaryTitle() {
		return summaryTitle;
	}

	public void setSummaryTitle(String summaryTitle) {
		this.summaryTitle = summaryTitle;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorEmail() {
		return creatorEmail;
	}

	public void setCreatorEmail(String creatorEmail) {
		this.creatorEmail = creatorEmail;
	}
	
	
	
	
	
	
	
	
	
	
}
