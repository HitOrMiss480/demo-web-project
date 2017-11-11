package edu.csupomona.cs480.util;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class EventDAOimplem implements CalendarDAO
{
	private JdbcTemplate jdbctemplate;

	@Override
	public void insertEvent(Event event) {
		//insert SQL code here 
		
	}

	@Override
	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	public JdbcTemplate getJdbctemplate() {
		return jdbctemplate;
	}

	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

}
