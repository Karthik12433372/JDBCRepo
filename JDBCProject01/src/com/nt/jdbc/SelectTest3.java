package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
		
		System.out.println("SelectTest3.main()");
		Scanner sc = null;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String initchars = null;
			if(sc!=null) {
				System.out.println("Enter initial chars of emp name: ");
				initchars = sc.next();
			}
			initchars = "'"+initchars+"%'";
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			if(conn != null) {
				st = conn.createStatement();
				String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE"+initchars;
				if(st!=null) {
					rs = st.executeQuery(query);
				}
				if(rs != null) {
					//int count = 0;
					boolean flag = false;
					while(rs.next()) {
						//count++;
						flag = true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
					}//while
					//if(count==0) {
					//	System.out.println("No Records Found");
					//}
					if(flag==false) {
						System.out.println("No records found");
					}
				}//if
			}
		}
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
