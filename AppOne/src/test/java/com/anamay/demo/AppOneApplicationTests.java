package com.anamay.demo;



import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.anamay.demo.exception.CustomerNotAddedException;
import com.anamay.demo.exception.CustomerNotFoundException;
import com.anamay.demo.model.Customer;
import com.anamay.demo.service.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class AppOneApplicationTests {
	
	private MockMvc mockmvc;
	
	ObjectMapper objectmapper= new ObjectMapper();

	
	@Mock
	private CustomerServiceImpl customerService;
	
	@InjectMocks
	private HomeController homeController;
	
	@BeforeAll
	private void init() {
		MockitoAnnotations.openMocks(this);
        this.mockmvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}
	
	@Test
	void testAddCustomer() throws Exception{
		Customer customer=new Customer(1,"Anamay");
		
		String jsonreq=objectmapper.writeValueAsString(customer);
		
		mockmvc.perform(post("/appone/addcustomer")
				.content(jsonreq)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	
	}
	
	@Test
	void testAddCustomer_Fail() throws Exception{
		Customer customer=new Customer();
		
		String jsonreq=objectmapper.writeValueAsString(customer);
		
		try {
		mockmvc.perform(post("/appone/addcustomer")
				.content(jsonreq)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		}
		catch(Exception e) {
			fail("NOt Added");
		}
	
	}
	
	@Test
	void getAllCustomersTest() throws Exception 
	{
	  List<Customer> customerlist= new ArrayList<Customer>();
	  Customer customer1=new Customer(1,"Anamay");
	  Customer customer2=new Customer(2,"Aditya");
	  Customer customer3=new Customer(3,"Deepak");
	  Customer customer4=new Customer(4,"Alok");
	  customerlist.add(customer1);
	  customerlist.add(customer2);
	  customerlist.add(customer3);
	  customerlist.add(customer4);
	  
	 Mockito.when(customerService.showAllEntries()).thenReturn(customerlist);
	  mockmvc.perform(MockMvcRequestBuilders
			  .get("/appone/showcustomers")
			  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$[0].customerName", is("Anamay")))
			  .andExpect(jsonPath("$[1].customerName", is("Aditya")));
	  
	}
	
	@Test
	void getAllCustomersTest_Fail() throws Exception 
	{
	  
	 try {
	  mockmvc.perform(MockMvcRequestBuilders
			  .get("/appone/showcustomers")
			  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk());
	  }
	 catch(Exception e) {
		 fail("No Customers Found");
	 }
	  
	}
	
	@Test
	void healthCheckTest() throws Exception 
	{
	  
	 Mockito.when(customerService.healthCheckMethod()).thenReturn("Health check Successfull");
	  mockmvc.perform(MockMvcRequestBuilders
			  .get("/appone/healthcheck")
			  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk());
	  
	}
	
	@Test
	void healthCheckTest_Fail() throws Exception 
	{
	  
	  //try {
	  mockmvc.perform(MockMvcRequestBuilders
			  .get("/appone/healthcheck")
			  .accept(MediaType.APPLICATION_JSON))
			  .andExpect(status().isOk());
	  //}
//	  catch(Exception e) {
//		  fail("Health Check Failed");
//	  }
	  
	}
	
	
	
}
