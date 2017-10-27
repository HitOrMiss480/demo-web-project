package edu.csupomona.cs480.util;


public class calendarA6 {
	
	private String event = "";
	private String location = "";
	private String date = "";
	private int numEvents = 0;
	
	
	
	public String EventDate (String event, String date)
	{
		String temp = "";
		temp = event + " is scheduled to be on " + date;
		return temp;
	}
	
	public boolean endDate(int dateToCheck, int dateInData) {
		return dateToCheck == dateInData;
	}
	
	public int product (int a, int b) {
		return a * b;
	}
	public boolean Empty() {
		if(date=="") {
			return true;
		}
		return false;
	}

	public Object currentDate() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
