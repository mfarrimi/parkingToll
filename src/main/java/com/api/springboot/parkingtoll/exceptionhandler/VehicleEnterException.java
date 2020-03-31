package com.api.springboot.parkingtoll.exceptionhandler;

public class VehicleEnterException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleEnterException(String message, Throwable cause) {
		super(message, cause);
	}

	public VehicleEnterException(String message) {
		super(message);
	}

	public VehicleEnterException(Throwable cause) {
		super(cause);
	}
}
