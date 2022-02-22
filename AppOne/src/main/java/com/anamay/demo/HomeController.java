package com.anamay.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anamay.demo.exception.CustomerNotAddedException;
import com.anamay.demo.exception.CustomerNotFoundException;
import com.anamay.demo.exception.HealthCheckFailed;
import com.anamay.demo.model.Customer;
import com.anamay.demo.service.CustomerServiceImpl;


@RestController
@RequestMapping("/appone")
public class HomeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private CustomerServiceImpl customerservice;
	
	@RequestMapping("")
    @ResponseBody
    String home() {
        LOG.info("sending hello response");
        return "Hello! This is home page";
    }
	
	@GetMapping("/healthcheck")
	public ResponseEntity<String> healthCheck() throws HealthCheckFailed{
		
		LOG.info("Health check called");
		String healthcheckstatus=customerservice.healthCheckMethod();
		ResponseEntity<String> response=new ResponseEntity<String>("Health Check Succefull", HttpStatus.OK);
		return response;
	
	}
	
	@PostMapping("/addcustomer")
	public ResponseEntity addCustomer( @Validated @RequestBody Customer customer) throws CustomerNotAddedException {
		
		LOG.info("Post mapping called for customer:"+customer.getCustomerId());
		Customer addedCustomer=customerservice.addCustomerDto(customer);
		ResponseEntity<Customer> response=new ResponseEntity<Customer>(addedCustomer, HttpStatus.CREATED);
		LOG.info("customer added");
		return response;
	
	}
	
	@GetMapping("/showcustomers")
	public ResponseEntity showAllCustomers() throws CustomerNotFoundException{
		
		LOG.info("Get mapping called for all customers");
		List<Customer> customerlist=customerservice.showAllEntries();
		LOG.info("Customer List fetched");
		return new ResponseEntity<List<Customer>>(customerlist,HttpStatus.OK);

	}
	

}
