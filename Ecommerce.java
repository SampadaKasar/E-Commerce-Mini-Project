package com.ecommerceproject;

import java.sql.SQLException;
import java.util.Scanner;

public  class Ecommerce implements EcommerceMiniProject {

	//Start of the application such as login, registration
	//select Admin or customer portal.
	public void ecommerceMain() {
		System.out.println("*     Welcome To InstaMart      *");
	
		System.out.println("Select A for Admin and C for Customer");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		// String C = sc.next();
		boolean f = true;
		try {
			if (str.startsWith("A") || str.startsWith("a")) {
				Login ln = new Login();
				ln.login_Admin();
				f = true;
			} else if (str.startsWith("C") || str.startsWith("c")) {
				System.out.println("If You have already registered please select L for Login otherwise select R for registration");
				String str1 = sc.next();
				if (str1.startsWith("L") || str1.startsWith("l")) {
					Login ln = new Login();
					ln.userLogin();
				} else if (str1.startsWith("R") || str1.startsWith("r")) {
					Registration rgn = new Registration();
					rgn.getUserDetails();
				} else {
					System.out.println("Invalid");
				}
			} else {
				System.out.println("Invalid input");
				f = false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		sc.close();
	}
	
	//ecommerce main 
	public static void main(String[] args) throws SQLException {
		Ecommerce ecom = new Ecommerce();
		ecom.ecommerceMain();
	}

}
