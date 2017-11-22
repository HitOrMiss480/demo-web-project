package edu.csupomona.cs480.DAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import edu.csupomona.cs480.data.*;


public class DataAccess {
	private  String userName = "homdev";
	private  String password = "homdev480";
	private  String dbms = "mysql";
	private  String serverName = "homdevdb.cylr0zjow1ge.us-west-1.rds.amazonaws.com";
	private  String database = "cs_480_hom";
	private  int portNumber = 3306;
	
	public  User getUser(String userId) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareCall("{call GetUser(?)}");
			stmt.setString(1, userId);
			User user  = new User();
			
			boolean isRS = stmt.execute();
			if(!isRS) {
				return null;
			}
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				user.setId(rs.getString("UserId"));
				user.setName(rs.getString("Name"));
				user.setUserName(rs.getString("UserName"));
			}
			// add get userevent and userorg code here
			
			return user;
		}
		catch (SQLException e) {
			stmt.close();
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	public  User createUser(User user) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareCall("{call CreateUser(?,?,?)}");
			stmt.setString(1, user.getId());
			stmt.setString(2, user.getName());
			stmt.setString(3, user.getUserName());
			int rs = stmt.executeUpdate();
			if(rs == 0) {
				return null;
			}
			return user;
		}
		catch (SQLException e) {
			stmt.close();
			throw e;
		}
	}
	
	// event
	public ArrayList<Events> getUserEvents(String userId) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareCall("{call GetUserEvents(?)}");
			stmt.setString(1, userId);
			ArrayList<Events> events = new ArrayList<Events>();
			Events event = new Events();
			
			boolean isRS = stmt.execute();
			if(!isRS) {
				return null;
			}
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				event.setEventId(rs.getString("EventId"));
				event.setEventName(rs.getString("EventName"));
				event.setDate(rs.getString("Date"));
				event.setPlanner(rs.getString("Planner"));
				events.add(event);
			}
			return events;
		}
		catch (SQLException e) {
			stmt.close();
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	public ArrayList<Events> getEvents() throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareCall("{call GetEvents()}");
			
			ArrayList<Events> events = new ArrayList<Events>();
			Events event = new Events();
			
			boolean isRS = stmt.execute();
			if(!isRS) {
				return null;
			}
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				event.setEventId(rs.getString("EventId"));
				event.setEventName(rs.getString("EventName"));
				event.setDate(rs.getString("Date"));
				event.setPlanner(rs.getString("Planner"));
				events.add(event);
			}
			return events;
		}
		catch (SQLException e) {
			stmt.close();
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	public ArrayList<Events> addUserEvents(ArrayList<Events> events,String userId) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			
			stmt = con.prepareCall("{call ClearUserEvent(?)}");
			stmt.setString(1, userId);
			int rs = stmt.executeUpdate();
			if(rs == 0) {
				return null;
			}
			
			for(Iterator<Events> i = events.iterator(); i.hasNext();) {
				Events event = i.next();
				stmt = con.prepareCall("{call CreateUserEvent(?,?)}");
				stmt.setString(1, event.getEventId());
				stmt.setString(2, userId);
				rs = stmt.executeUpdate();
				if(rs == 0) {
					return null;
				}
			}
			return events;
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	public Events createEvent(Events event) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		try {
			stmt = con.prepareCall("{call CreateEvent(?,?,?,?)}");
			stmt.setString(1, event.getEventId());
			stmt.setString(2, event.getEventName());
			stmt.setString(3, event.getDate());
			stmt.setString(4, event.inPlanner());
			int rs = stmt.executeUpdate();
			if(rs == 0) {
				return null;
			}
			return event;
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	public ArrayList<Events> GetUserEventsByOrg(List<Organizations> list) throws SQLException{
		CallableStatement stmt = null;
		Connection con = getConnection();
		ArrayList<Events> events = new ArrayList<Events>();
		Events event = new Events();
		try {
			for(Organizations orgId : list) {
				
				stmt = con.prepareCall("{call GetUserEventsByOrg(?)}");
				stmt.setString(1, orgId.getOrgId());
				boolean isRS = stmt.execute();
				if(!isRS) {
					return null;
				}
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
					event.setEventId(rs.getString("EventId"));
					event.setEventName(rs.getString("EventName"));
					event.setDate(rs.getString("Date"));
					event.setPlanner(rs.getString("Planner"));
					events.add(event);
				}
			}
			return events;
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			stmt.close();
		}
	}
	
	
	private  Connection getConnection() throws SQLException{
		java.sql.Connection con = null;
		Properties conProp = new Properties();
		conProp.put("user", this.userName);
		conProp.put("password", this.password );
		
		String url =  "jdbc:" + this.dbms + "://" +
                this.serverName +
                ":" + this.portNumber + "/"+
                this.database +"?";
		
		con = DriverManager.getConnection(url,conProp);
		
		return con;
	}
}
