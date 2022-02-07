package com.anamay.demo.exception;

public class HealthCheckFailed extends Exception{
	
	public HealthCheckFailed() {
		super("Health Check Failed");
	}
}
