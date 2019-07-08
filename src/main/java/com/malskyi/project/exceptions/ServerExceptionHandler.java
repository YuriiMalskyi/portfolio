package com.malskyi.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.malskyi.project.domain.response.ErrorMessage;

@ControllerAdvice
public class ServerExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessage> handleExceptions(Exception e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = UserServiceException.class)
	public ResponseEntity<ErrorMessage> handleUserServiceException(UserServiceException e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = CommodityNotFoundException.class)
	public ResponseEntity<ErrorMessage> hadleCommodityNotFoundException(UserServiceException e, WebRequest req){
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NO_CONTENT);
	}
}
