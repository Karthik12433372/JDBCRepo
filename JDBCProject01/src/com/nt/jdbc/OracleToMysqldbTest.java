package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMysqldbTest {
	
	private static final String MYSQL_INSERT_STUDENT = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String ORACLE_SELECT_STUDENT = "SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	

	public static void main(String[] args) {

		Connection oracon = null, mysqlcon = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//Register Drivers
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establish the Connection
			oracon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			mysqlcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/karthik","root","Karthik@123");
			
			//create statement objects
	        if(oracon != null)
	        	st = oracon.createStatement();
	        if(mysqlcon != null)
	        	ps = mysqlcon.prepareStatement(MYSQL_INSERT_STUDENT);
	        
	        //send and execute SELECT query in oracle db s/w and get ResultSet obj
	        if(st != null)
	        	rs = st.executeQuery(ORACLE_SELECT_STUDENT);
	        
	        if(rs != null && ps != null) {
	        	while(rs.next()) {
	        		int no = rs.getInt(1);
	        		String name = rs.getString(2);
	        		String addrs = rs.getString(3);
	        		float avg = rs.getFloat(4);
	        		
	        		ps.setInt(1, no);
	        		ps.setString(2, name);
	        		ps.setString(3, addrs);
	        		ps.setFloat(4, avg);
	        		
	        		ps.executeUpdate();
	        	}//while
	        	System.out.println("Records are copied from oracle db s/w to mysql db s/w");
	        	
	        }//if
	        
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Records are not copied from oracle db s/w to mysql db s/w");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Problems in app. execution");
		}
		finally {
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(mysqlcon!=null)
					mysqlcon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(oracon!=null)
					oracon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main

}//class
