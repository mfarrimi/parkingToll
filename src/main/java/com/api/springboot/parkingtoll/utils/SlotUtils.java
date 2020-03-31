package com.api.springboot.parkingtoll.utils;

import java.time.LocalDateTime;

import com.api.springboot.parkingtoll.entity.Slot;

/**
 * Utility class for slot management : block and free
 * 
 * @author
 *
 */
public final class SlotUtils {

	private SlotUtils() {

	}

	/**
	 * Mark Slot as taken and associate Vehicle
	 * 
	 * @param slot
	 * @param vehicleRegistration
	 */
	public static void blockSlot(Slot slot, String vehicleRegistration) {
		// add Vehicle to slot
		slot.setAvailable(false);
		slot.setVehicleRegistration(vehicleRegistration);
		slot.setWhenOccupied(LocalDateTime.now());
	}

	/**
	 * Mark Slot as free
	 * 
	 * @param slot
	 * @param vehicleRegistration
	 */
	public static void freeSlot(Slot slot) {
		// add Vehicle to slot
		slot.setAvailable(true);
		slot.setVehicleRegistration(null);
		slot.setWhenOccupied(null);
	}
}
