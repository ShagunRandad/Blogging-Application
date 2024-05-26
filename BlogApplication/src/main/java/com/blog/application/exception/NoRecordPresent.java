package com.blog.application.exception;

public class NoRecordPresent extends RuntimeException {
	
	public NoRecordPresent(){
		super("No Record Found");
	}
	
	public NoRecordPresent(String message){
		super(message);
	}
}
