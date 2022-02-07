package com.anamay.demo.model;

import org.mapstruct.Mapper;

@Mapper(
	    componentModel = "spring"
		)
public interface CustomerMapper {
	
	CustomerDto toDto(Customer cust);
	
	Customer fromDto(CustomerDto custdto);
}
