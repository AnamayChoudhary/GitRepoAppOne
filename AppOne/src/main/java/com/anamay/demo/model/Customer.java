package com.anamay.demo.model;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;


@Component
public class Customer {
	
	private int customerId;
	
	
	public Customer(int customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}
	
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}


	private String customerName;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
