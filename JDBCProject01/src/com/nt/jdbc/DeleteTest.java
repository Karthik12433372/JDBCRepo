package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest {

	public static void main(String[] args) {

		Scanner sc = null;
		Connection conn = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			String city = null;
			if(sc != null) {
				System.out.println("Enter student address(city name)");
				city = sc.next();
			}
			city = "'"+city+"'";

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			if(conn != null)
				st = conn.createStatement();
			String query = "DELETE FROM STUDENT WHERE SADD ="+city;
			System.out.println(query);

			int count = 0;
			if(st != null) 
				count = st.executeUpdate(query);

			if(count == 0)
				System.out.println("No records found to be deleted");
			else
				System.out.println("No of records that are effected :"+count);
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid col names or table names or SQL keywords");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(conn!=null)
					conn.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}
}






