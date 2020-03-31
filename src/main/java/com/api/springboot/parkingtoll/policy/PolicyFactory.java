package com.api.springboot.parkingtoll.policy;

import java.time.LocalDateTime;

import com.api.springboot.parkingtoll.entity.Bill;

/**
 * Factory class responsible of instantiating The right Policy implementation
 * according to Vehicle type
 * 
 * @author
 *
 */
public class PolicyFactory {

	public static Bill createPolicy(String vehicleType, LocalDateTime startDate, String vehicleRegistration) {
		switch (vehicleType) {
		case "GAS":
			return new GasPolicy().creatBill(startDate, vehicleRegistration);
		case "20K":
			return new Electric20KPolicy().creatBill(startDate, vehicleRegistration);
		case "50K":
			return new Electric50KPolicy().creatBill(startDate, vehicleRegistration);
		default:
			return null;
		}
	}
}
