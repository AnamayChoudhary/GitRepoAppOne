package com.anamay.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anamay.demo.HomeController;
import com.anamay.demo.exception.CustomerNotAddedException;
import com.anamay.demo.exception.CustomerNotFoundException;
import com.anamay.demo.exception.HealthCheckFailed;
import com.anamay.demo.model.Customer;
import com.anamay.demo.model.CustomerDto;
import com.anamay.demo.model.CustomerMapper;
import com.anamay.demo.repository.CustomerRepository;
import com.anamay.demo.model.CustomerMapperImpl;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static CustomerMapper mapper=new CustomerMapperImpl();
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	CustomerRepository customerrepository;

	/*
	 * Method to add a new customer
	 */
	@Override
	public Customer addCustomerDto(Customer customer) throws CustomerNotAddedException{
		LOG.info("addCustomerDto called in service layer");
		CustomerDto customerdto=mapper.toDto(customer);
		CustomerDto customerdtoadded;
		
		try {
			customerdtoadded=customerrepository.save(customerdto);
			LOG.info(customerdtoadded.getCustomerName()+" added");
			if(customerdtoadded==null) {
				throw new CustomerNotAddedException("Customer Not Added");
			}
		}
		catch(CustomerNotAddedException e) {
			throw e;
		}
		Customer customervo=mapper.fromDto(customerdtoadded);
		return customervo;
	}

	/*
	 * Method to show all customers
	 */
	@Override
	public List<Customer> showAllEntries() throws CustomerNotFoundException {
		LOG.info("showAllEntries called in service layer");
		
		List<CustomerDto> customerdtolist=customerrepository.findAll();
		if(customerdtolist.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		Iterator<CustomerDto> dtoiterator = customerdtolist.iterator();
		List<Customer> customervolist = new ArrayList<Customer>();
		
		while(dtoiterator.hasNext()) {
			customervolist.add(mapper.fromDto(dtoiterator.next()));
		}
		
		return customervolist;
	}

	/*
	 * Method to update a Customer
	 */
	
	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		LOG.info("updateCustomer called in service layer");
		Customer customertemp=null;
		CustomerDto customerdto=mapper.toDto(customer);
		
		Optional<CustomerDto> customerdtoretrieved=customerrepository.findById(customerdto.getCustomerId());
		
		if(customerdtoretrieved.isEmpty()) {
			throw new CustomerNotFoundException();
		}
		
		customerdto=customerrepository.save(customerdto);
		customertemp=mapper.fromDto(customerdto);
		return customertemp;
	}

	@Override
	public String healthCheckMethod() throws HealthCheckFailed {
		LOG.info("Health check called in service layer");
		List<CustomerDto> customerdtolist=customerrepository.findAll();
		if(customerdtolist.isEmpty()) {
			throw new HealthCheckFailed();
		}
		return "Health check Succesfull";
	}
	
	
	
	

}
