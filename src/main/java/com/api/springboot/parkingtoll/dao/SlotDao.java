package com.api.springboot.parkingtoll.dao;

import com.api.springboot.parkingtoll.entity.Slot;

public interface SlotDao {

	/**
	 * Find available slot according to Vehicle type
	 * 
	 * @param type of the Vehicle (Gas,20K,50K)
	 * @return Slot if available null otherwise
	 */
	public Slot findAvailableSlot(String type);

	/**
	 * Add Vehicle to slot 
	 * @param slot
	 * @param vehicleRegistration
	 * @return the updated slot
	 */
	public Slot addVehicle(Slot slot, String vehicleRegistration);

	/**
	 * get the slot occupied by the Vehicle
	 * 
	 * @param vehicleRegistration
	 * @return The slot occupied by the Vehicle null otherwise
	 */
	public Slot getAssociatedSlotToVehicle(String vehicleRegistration);

	/**
	 * 
	 * @param slot
	 * @return
	 */
	public void removeVehicle(Slot slot);
}
