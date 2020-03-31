package com.api.springboot.parkingtoll.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import javassist.tools.web.BadHttpRequest;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(VehicleEnterException exc) {
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(VehicleExitException exc) {
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc) {
		ErrorResponse error = new ErrorResponse();

		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("System ERROR");
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
