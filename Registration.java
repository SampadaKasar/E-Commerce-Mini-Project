package com.ecommerceproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

//Created Registration process format.
public class Registration {
	
	//To get user Details 
	public void getUserDetails() {
		System.out.println("Register here");
		System.out.println("Please Enter your details");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name : ");
		String user_name = sc.next();
		System.out.println("Enter your mobile number : ");
		String user_mobilenumber = sc.next();
		System.out.println("Enter your address : ");
		String user_address = sc.next();
		System.out.println("Enter your email id : ");
		String user_emailid = sc.next();
		System.out.println("Enter your Password : ");
		String password = sc.next();

		PreparedStatement pst = null;
		GetConnection getcon = new GetConnection();
		Connection con = null;
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement(
					"Insert into registration (user_name, user_mobilenumber, user_address, user_emailid, password) values (?,?,?,?,?)");
			pst.setString(1, user_name);
			pst.setString(2, user_mobilenumber);
			pst.setString(3, user_address);
			pst.setString(4, user_emailid);
			pst.setString(5, password);

			pst.execute();
			System.out.println("Registered Successfully");
			System.out.println("Please Login");
			Login ln = new Login();
			ln.userLogin();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
				pst.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
