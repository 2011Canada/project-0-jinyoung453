package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//factory design pattern?
//factory is an object that builds other objects -> based on provided configuration

public class ConnectionFactory {
	//this holds all of our connections
	private Connection[] conn;
	
	public ConnectionFactory(int numberOfConnections) {
		
		String url = "jdbc:postgresql://canada2011-database.cvb3n4gkzaox.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=entertainment720";
		String user = "postgres";
		String password = "jinyounG9)";
		try {
			this.conn = new Connection[numberOfConnections];
			for(int i=0; i<this.conn.length; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				this.conn[i] = conn;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
