package com.BankOfIndia.DBConnection;

import java.sql.*;

public class DBConnectionSingleton {
	public static Connection con=null;
	private DBConnectionSingleton() {

	}
	public static Connection getInstance() {
		String url="jdbc:mysql://localhost:3305/bankmanagementdb";
		String username="root";
		String password="root";
		if(con==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 con= DriverManager.getConnection(url, username, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		return con;
	}




}
