package com.ecommerceproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommercedb", "root", "root");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Invalid Password and Db name and username");
			e.printStackTrace();
		}
		return con;
	}

}
