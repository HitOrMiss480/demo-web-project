package edu.csupomona.cs480.DAL;

import java.sql.*;
import java.util.Properties;

import edu.csupomona.cs480.data.User;


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
