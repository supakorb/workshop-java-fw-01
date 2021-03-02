package com.example.kbtg.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

	@ExceptionHandler(UserNotFoundException.class)
//	@ExceptionHandler({ UserNotFoundException.class, RuntimeException.class })
	public ResponseEntity<ErrorResponse> handleUserNotFound(Exception e) {
		ErrorResponse response = new ErrorResponse(1234, e.getMessage());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
