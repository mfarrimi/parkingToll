package com.api.springboot.parkingtoll.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.springboot.parkingtoll.dao.SlotDao;
import com.api.springboot.parkingtoll.entity.Bill;
import com.api.springboot.parkingtoll.entity.Slot;
import com.api.springboot.parkingtoll.policy.PolicyFactory;

@Service
public class SlotServiceImpl implements SlotService {

	private static final String VEHICLE_NOT_FOUND_ERROR = "No such Vehicle found in parking ";

	private static final String VEHICLE_ALREADY_EXIST_ERROR = "Vehicle with same registration already exists in parking ";

	private static final String NO_SLOTS_AVAILABLE_ERROR = "no slots available";

	private SlotDao slotDao;

	private static String errorMsg;

	@Autowired
	public SlotServiceImpl(SlotDao slotDao) {
		this.slotDao = slotDao;
	}

	@Override
	@Transactional
	public Slot manageCarIn(String vehicleType, String vehicleRegistration) {
		Slot availableSlot = slotDao.findAvailableSlot(vehicleType);
		if (availableSlot == null) {
			setErrorMsg(NO_SLOTS_AVAILABLE_ERROR);
			return null;
		}

		Slot associatedSlot = slotDao.getAssociatedSlotToVehicle(vehicleRegistration);
		if (associatedSlot != null) {
			setErrorMsg(VEHICLE_ALREADY_EXIST_ERROR);
			return null;
		}

		return slotDao.addVehicle(availableSlot, vehicleRegistration);
	}

	@Override
	@Transactional
	public Bill manageCarOut(String vehicleRegistration) {
		Slot associatedSlot = slotDao.getAssociatedSlotToVehicle(vehicleRegistration);
		if (associatedSlot == null) {
			setErrorMsg(VEHICLE_NOT_FOUND_ERROR);
			return null;
		}

		LocalDateTime whenOccupied = associatedSlot.getWhenOccupied();
		String vehicleType = associatedSlot.getVehicleType();

		slotDao.removeVehicle(associatedSlot);

		return PolicyFactory.createPolicy(vehicleType, whenOccupied, vehicleRegistration);
	}

	@Override
	public String getErrorMsg() {
		return errorMsg;
	}

	private static void setErrorMsg(String errorMsg) {
		SlotServiceImpl.errorMsg = errorMsg;
	}
}
