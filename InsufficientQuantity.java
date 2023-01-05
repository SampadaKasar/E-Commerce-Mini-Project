package com.ecommerceproject;


public class InsufficientQuantity extends RuntimeException{
	public InsufficientQuantity(String message) {
		super(message);
	}
}
