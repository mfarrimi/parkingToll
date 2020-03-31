package com.api.springboot.parkingtoll.exceptionhandler;

public class VehicleExitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleExitException(String message, Throwable cause) {
		super(message, cause);
	}

	public VehicleExitException(String message) {
		super(message);
	}

	public VehicleExitException(Throwable cause) {
		super(cause);
	}
}

