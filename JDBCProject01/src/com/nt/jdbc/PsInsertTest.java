package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertTest {

	private static final String STUDENT_INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
	public static void main(String[] args) {

		Scanner sc = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter the students count :");
				count = sc.nextInt();
			}
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/karthik","root","Karthik@123");

			if(conn!=null) {
				ps = conn.prepareStatement(STUDENT_INSERT_QUERY);
				if(ps!=null && sc != null) {
					for(int i =1;i <=count;i++) {
						System.out.println("Enter"+i+"student details :");
						System.out.println("Enter student number :");
						int no = sc.nextInt();
						System.out.println("Enter student name :");
						String name = sc.next();
						System.out.println("Enter student Address :");
						String add = sc.next();
						System.out.println("Enter student average: ");
						float avg = sc.nextFloat();

						ps.setInt(1, no);
						ps.setString(2, name);
						ps.setString(3, add);
						ps.setFloat(4, avg);

						int result = ps.executeUpdate();
						if(result == 0) 
							System.out.println(i+" student details not inserted");
						else
							System.out.println(i+" student details are inserted");

					}
				}
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
				if(ps!=null)
					ps.close();
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
