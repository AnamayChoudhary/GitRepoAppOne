package com.anamay.demo.exception;

public class CustomerNotFoundException extends Exception {
	
	public CustomerNotFoundException() {
		super("Customer Not Found");
	}

}
