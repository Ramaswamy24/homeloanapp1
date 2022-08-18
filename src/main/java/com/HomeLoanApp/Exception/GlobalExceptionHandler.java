package com.HomeLoanApp.Exception;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmptyInputException.class)		//user generated
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Exception Occured "+emptyInputException.getErrorCode()+" : "+emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class)								//predefined
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException elementException){
		return new ResponseEntity<String>("Request can't be completed",HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NullPointerException.class)								//predefined
	public ResponseEntity<String> handleNullPointerException(NullPointerException e){
		return new ResponseEntity<String>("Requested Property not availaible",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)								//predefined
	public ResponseEntity<String> handleNumberFormatException(IllegalArgumentException e){
		return new ResponseEntity<String>("Requested Value is not valid",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidFormatException.class)								//predefined
	public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException e){
		return new ResponseEntity<String>("Requested Property not valid",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)								//predefined
	public ResponseEntity<String> handleInvalidFormatException(DataIntegrityViolationException e){
		return new ResponseEntity<String>("Requested Property is a Reference to the another Entity",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)								//predefined
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		return new ResponseEntity<String>(e.getBindingResult().getFieldError().getField()+" : "+e.getBindingResult().getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
	}
}
