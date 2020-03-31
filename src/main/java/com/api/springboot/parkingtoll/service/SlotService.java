package com.api.springboot.parkingtoll.service;

import com.api.springboot.parkingtoll.entity.Bill;
import com.api.springboot.parkingtoll.entity.Slot;

public interface SlotService {

	/**
	 * manage Vehicle entry to parking
	 * 
	 * @param type    of the Vehicle (Gas,20K,50K)
	 * @param vehicle registration
	 * @return the updated slot null if no slot is available
	 */
	public Slot manageCarIn(String vehicleType, String vehicleRegistration);

	/**
	 * manage Vehicle exit from parking
	 * 
	 * @param vehicleRegistration
	 */
	public Bill manageCarOut(String vehicleRegistration);

	/**
	 * 
	 * @return error message
	 */
	public String getErrorMsg();
}
