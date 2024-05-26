package com.blog.application.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(NoRecordPresent.class)
	public ResponseEntity<?> noRecordPresent(NoRecordPresent noRecordPresent){
		return new ResponseEntity(noRecordPresent.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<?> noResourceFoundException(){
		return new ResponseEntity("Bad Request! No Resource Found",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>  methodArgumentNotValidException(
			MethodArgumentNotValidException ethodArgumentNotValidException) {
		Map<String,String> err=new HashMap<String, String>();
		ethodArgumentNotValidException.getBindingResult().getAllErrors().forEach(error->{
		String feildName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		err.put(feildName, message);
		});
	
		return new ResponseEntity<Map<String,String>>(err,HttpStatus.NOT_FOUND);
	}
}
