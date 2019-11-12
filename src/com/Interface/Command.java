package com.Interface;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

	public String execute(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException;
	
		
   //
	
}
