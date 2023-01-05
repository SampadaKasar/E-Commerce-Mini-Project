package com.ecommerceproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//To Check the Product Quantity, And created user define exception to display Proper Message in case of insufficient quantity. 
public class CheckQuantity {
	public void poductCheck(int quantity , int product_id) { 
		
		int i = product_id;
		GetConnection getcon = new GetConnection();
		PreparedStatement pst = null;
		Connection con = null;
		try {
			con = getcon.getConnection();
			pst = con.prepareStatement("select product_quantity from product where product_id ='"+i+"'");
			ResultSet rs = pst.executeQuery();
			
			int dbquantity=0;
			while(rs.next()) {
				dbquantity=rs.getInt(1);
			}
			if(quantity>dbquantity) {
				throw new InsufficientQuantity("Sufficient Quantity is Not Available");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
