package com.ecommerceproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login  {
	
	//User Login Portal.
	public void userLogin() throws SQLException {
		System.out.println("Login here");
		System.out.println("Please Enter your username and password");
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter User Name : ");
		String username = sc.next();
		System.out.println("Enter Password : ");
		String password = sc.next();

		PreparedStatement pst = null;
		int userid = 0;
		GetConnection getcon = new GetConnection();
		Connection con = getcon.getConnection();
		pst = con.prepareStatement(
				"SELECT user_name, password, user_id FROM registration WHERE user_name =? && password =?");

		pst.setString(1, username);
		pst.setString(2, password);

		// pst.setInt(3, userid);

		ResultSet rs = pst.executeQuery();
		String dbusername = null;
		String dbpassword = null;
		int dbuserid = 0;
		
		while (rs.next()) {
			dbusername = rs.getString(1);
			dbpassword = rs.getString(2);
			dbuserid = rs.getInt(3);
		}
		
		boolean f = true;
		if (username.equals(dbusername) && password.equals(dbpassword)) {
			System.out.println("Login Successfully");
			System.out.println("Welcome : " + dbusername);
			System.out.println("User id : " + dbuserid);
			
			Product pd = new Product();
			pd.getProductDetails();

			f = true;
		} else {
			System.out.println("User Name and Password Invalid");
			System.out.println("Please Enter Again ");
			f = false;
			Login lg1 = new Login();
			lg1.userLogin();
		}
		con.close();
		pst.close();

	}

	//Admin Login Portal
	public void login_Admin() throws SQLException {

		System.out.println("Login here");
		System.out.println("Please Enter your username and password");
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter User Name : ");
		String username = sc.next();
		System.out.println("Enter Password : ");
		String password = sc.next();

		PreparedStatement pst = null;

		GetConnection getcon = new GetConnection();
		Connection con = getcon.getConnection();
		pst = con.prepareStatement("SELECT user_name, password FROM admin WHERE user_name =? && password =? ");

		pst.setString(1, username);
		pst.setString(2, password);

		ResultSet rs = pst.executeQuery();
		String dbausername = null;
		String dbapassword = null;
		while (rs.next()) {
			dbausername = rs.getString(1);
			dbapassword = rs.getString(2);

		}
		
		boolean f = true;
		if (username.equals(dbausername) && password.equals(dbapassword)) {
			Admin ad = new Admin();
			ad.getAdmin();
			f = true;
		} else {
			System.out.println("User Name and Password Invalid ");
			System.out.println("Please Enter Again ");
			f = false;
			Login l = new Login();
			l.login_Admin();
		}
		con.close();
		pst.close();
		sc.close();
	}

}
