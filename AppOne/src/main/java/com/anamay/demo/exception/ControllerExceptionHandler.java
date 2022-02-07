package com.anamay.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String customerNotFound(CustomerNotFoundException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String customerNotAdded(CustomerNotAddedException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
	public String healthCheckFailedM(HealthCheckFailed exception) {
		return exception.getMessage();
	}
	

}
