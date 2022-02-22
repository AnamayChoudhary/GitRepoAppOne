package com.anamay.demo.businessobbject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.anamay.demo.model.Customer;
import com.anamay.demo.model.CustomerDto;
import com.anamay.demo.repository.CustomerRepository;

public class BusinessLogic {
	@Autowired
	CustomerRepository customerrepository;
	
	public List<CustomerDto> callDBForHealthCheck(){
		List<CustomerDto> customerdtolist=customerrepository.findAll();
		return customerdtolist;
		
	}

}
