package com.anamay.demo.cucumber;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.assertj.core.util.Arrays;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.anamay.demo.model.Customer;
import com.anamay.demo.model.CustomerMapper;
import com.anamay.demo.model.CustomerMapperImpl;
import com.anamay.demo.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
public class CucumberStepDefinition{
	
	private List<Customer> expectedCustomers;
	private List<Customer> actualCustomers;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	ObjectMapper objectmapper;
	
	
	@Before
	public void setup() {
		expectedCustomers=new ArrayList<>();
		actualCustomers=new ArrayList<>();
	}
	
	@Given("^the following customers$")
    public void the_following_customers(final List<Customer> customers) throws Throwable {
		expectedCustomers.addAll(customers);
        throw new PendingException();
    }

    @When("^the client calls $")
    public void the_client_calls() throws JsonMappingException, JsonProcessingException {
    	actualCustomers.add((Customer) Arrays.asList(objectmapper.readValue(testRestTemplate.getForEntity("http://localhost:9999/appone/showcustomers", String.class).getBody(), Customer[].class)));
    }

    
    @Then("^the client receives list of customers$")
    public void the_client_receives_list_of_customers() throws Throwable {
    	assertEquals(actualCustomers, expectedCustomers);
    }

}