package com.ecommerceproject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//Product Portal design such as Display Products, Buy Products
public class Product {
	GetConnection getcon = new GetConnection();//To get database connection

	//To Display Products
	public void getProductDetails() {
		System.out.println("Products");
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement("SELECT * FROM product ORDER BY product_name");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

//				Set<String> set = new TreeSet<String>();
//
//				set.add(rs.getString(2));

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString(2).toUpperCase());

				System.out.println("Product Id : " + rs.getInt(1));
				// System.out.println("Name : " +set );
				System.out.println("Name : " + list);
				System.out.println("Description : " + rs.getString(3));
				System.out.println("Quantity : " + rs.getInt(4));
				System.out.println("Price per Unit : " + rs.getDouble(5));
				System.out.println(" ****** ");
			}
			rs.close();

			Product p = new Product();
			p.getProduct();

		} catch (SQLException e) {
			System.out.println("Product Not Found");
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

   //To Buy Products.
	public void getProduct() throws SQLException {
		System.out.println("Enter Product Id of desired Product");
		System.out.println("To Exit Press 0 (Zero)");

		Scanner sc = new Scanner(System.in);
		int pId = sc.nextInt();
		if (pId != 0) {
			PreparedStatement pst = null;
			PreparedStatement pst2 = null;
			Connection con = null;
			try {
				con = getcon.getConnection();
				pst = con.prepareStatement(
						"SELECT product_id, product_name, product_quantity,product_price,product_description FROM product where product_id= ? ");
				pst.setInt(1, pId);
				ResultSet rs = pst.executeQuery();

				int quantity = 0;
				double TotalPrice = 0;
				int Totalqa = 0;
				while (rs.next()) {
					System.out.println("Enter Quantity : ");
					Scanner sc1 = new Scanner(System.in);
					quantity = sc1.nextInt();
					int dbpqua = rs.getInt(3);
					CheckQuantity cq = new CheckQuantity();
					try {
						cq.poductCheck(quantity, pId);
						System.out.println("Select only One - A for Add Cart || C for Cancel");
						String str = sc.next();

						double dbpprice = rs.getDouble(4);

						TotalPrice = quantity * dbpprice;
						Totalqa = dbpqua - quantity;

						if (str.startsWith("A") || str.startsWith("a")) {

							con = getcon.getConnection();
							pst = con.prepareStatement(
									"Insert into product_purchased (product_id, product_name, product_quantity,product_price, total_product_price, product_description)values((select product_id from product where product_id = ?), ?,?,?,?,?)");
							//pst.setInt(1, rs.getInt(1));
							pst.setInt(1, rs.getInt(1));
							pst.setString(2, rs.getString(2));
							pst.setInt(3, quantity);
							pst.setDouble(4, rs.getDouble(4));
							pst.setDouble(5, TotalPrice);
							pst.setString(6, rs.getString(5));

							pst.execute();
							
							pst2 = con.prepareStatement("update product set product_quantity=? where product_id=?");
							pst2.setInt(1, Totalqa);
							pst2.setInt(2, pId);
							pst2.execute();

							System.out.println("Order Added Successfully");
							System.out.println("Your Product : " + rs.getString(2));
							System.out.println("Your Product's Quantity : " + quantity);
							System.out.println("Your Product's Total Price : " + TotalPrice);

							System.out.println("To Buy Product Enter B : ");
							System.out.println("To Buy Another products Select S Otherwise E for Exit");

							String s = sc.next();
							if (s.startsWith("B") || s.startsWith("b")) {
								System.out.println("Order Placed Successfully");
								System.out.println("*** Thank You for Shopping ***");
							} else if (s.startsWith("S") || s.startsWith("s")) {
								Product p = new Product();
								p.getProductDetails();
							} else if (s.startsWith("E") || s.startsWith("e")) {
								System.out.println("* Thank You For Shopping *");
							} else {
								System.out.println("Invalid Input");
								System.out.println("Exit");
							}

						} else if (str.startsWith("C") || str.startsWith("c")) {

							System.out.println("Cancelled");
							Product pd = new Product();
							pd.getProductDetails();

						} else {
							System.out.println("Invalid Input");
							System.out.println("Exit");
						}
					
					}catch(InsufficientQuantity e) {
						System.out.println(e);
					}
					
					
				
				}
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
		} else {
			Ecommerce ecom = new Ecommerce();
			ecom.ecommerceMain();
		}

	}

	
	
	
	
}
