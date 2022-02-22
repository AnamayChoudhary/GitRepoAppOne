package com.anamay.demo.service;

import java.util.List;

import com.anamay.demo.exception.CustomerNotAddedException;
import com.anamay.demo.exception.CustomerNotFoundException;
import com.anamay.demo.exception.HealthCheckFailed;
import com.anamay.demo.model.Customer;

public interface CustomerService {
	public Customer addCustomerDto(Customer customer) throws CustomerNotAddedException;
	public List<Customer> showAllEntries() throws CustomerNotFoundException;
	public String healthCheckMethod() throws HealthCheckFailed;
}
