package com.ecommerceproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

	GetConnection getcon = new GetConnection();//To get the connection 

	//Admin portal design such as Add product, User History, user list, Check Quantity.
	public void getAdmin() {
		System.out.println("Welcome To Admin Portal");
		System.out.println(
				"Please Select Number (1 for Add product , 2 for User History, 3 for user list, 4 for Check Quantity, 5 for Exit )");

		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		Admin admin = new Admin();
		switch (number) {
		case 1:
			admin.addProductDetails();
			break;
		case 2:
			admin.userProductHistory();
			break;
		case 3:
			admin.getUserList();
			break;
		case 4:
			admin.getQuantityDetails();
			break;
		case 5:
			Ecommerce ec = new Ecommerce();
			ec.ecommerceMain();
			break;
		default:
			System.out.println("Invalid Number");
		}
	}

	// Add Product into Database.
	public void addProductDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of products to be added : ");
		
		int count = sc.nextInt();
		if(count ==0) {
			System.out.println("Exit");
			Admin a = new Admin();
			a.getAdmin();
		}
		
		for (int i = 1; i <= count; i++) {

			Scanner sc1 = new Scanner(System.in);
			System.out.println("Add Product");

			System.out.println("Enter Product Name : ");
			String pname = sc1.nextLine();

			System.out.println("Enter Product Description :");
			String desc = sc1.nextLine();

			System.out.println("Enter Product Quantity : ");
			int pqua = sc1.nextInt();

			System.out.println("Enter Product Price : ");
			double pprice = sc1.nextDouble();

			PreparedStatement pst = null;
			try {
				Connection con = getcon.getConnection();
				pst = con.prepareStatement(
						"Insert into Product (product_name , product_description , product_quantity , product_price) values (?,?,?,?)");
				pst.setString(1, pname);
				pst.setString(2, desc);
				pst.setInt(3, pqua);
				pst.setDouble(4, pprice);
				pst.execute();
				System.out.println("Product Added Successfully");
				

			} catch (SQLException e) {

				e.printStackTrace();
			}
			
		}
		
		Admin adm = new Admin();
		adm.getAdmin();
		sc.close();
	}

	
	// To Get user history 
	public void getUserList() {
		System.out.println("User list");
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement("SELECT * FROM registration");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				System.out.println(" User_ID : " + rs.getInt(1) + " User_Name : " + rs.getString(2)
						+ " User_Mobile_Number : " + rs.getString(3) + " User_ Address : " + rs.getString(4)
						+ " User_Email_ID : " + rs.getString(5));
			}
			rs.close();
			Admin adm = new Admin();
			adm.getAdmin();

		} catch (SQLException e) {
			System.out.println("User Not Found");
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

	// To Get Quantity Details product Id wise.
	public void getQuantityDetails() {
		System.out.println("Quantity Details");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Product Id : ");
		int productId = sc.nextInt();
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement(
					"SELECT product_id, product_name, product_quantity FROM product where product_id ='" + productId
							+ "'");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				System.out.println("Quantity Product Id wise : ");
				System.out.println("Product Id = " + rs.getInt(1) + " Product Name = " + rs.getString(2)
						+ " Product Quantity = " + rs.getInt(3));
			}
			
			System.out.println("To Check Another Product's Quantity Enter Y Otherwise E for Exit");
			String str = sc.next();
			Admin ad = new Admin();
			if(str.startsWith("Y")|| str.startsWith("y")) {
				ad.getQuantityDetails();
			}else if(str.startsWith("E")|| str.startsWith("e")) {
				ad.getAdmin();
			}
			else
			{
				System.out.println("Invalid Input");
				System.out.println("Exit");
			}

		} catch (SQLException e) {
			System.out.println("Product Not Found");
			e.printStackTrace();
		}

	}
	
	//To Get user history for product purchase details.
	public void userProductHistory() {
		System.out.println("User History");
		PreparedStatement pst = null;
		Connection con = null;
		
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement("Select * from product_purchased");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				
			ArrayList list = new ArrayList<>();
			list.add(rs.getInt(1));
			list.add(rs.getInt(2));
			list.add(rs.getString(3));
			list.add(rs.getInt(4));
			list.add(rs.getDouble(5));
			list.add(rs.getDouble(6));
			list.add(rs.getString(7));
			
			System.out.println(list);
			}
			
			Admin a = new Admin();
			a.getAdmin();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}




	
}
