package com.api.springboot.parkingtoll.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.springboot.parkingtoll.dao.SlotDaoImpl;
import com.api.springboot.parkingtoll.entity.Bill;
import com.api.springboot.parkingtoll.entity.Slot;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SlotServiceImplTest {

	private static final String VEHICLE_TYPE = "GAS";
	private static final String VEHICLE_REGISTRATION = "AB-00-CD";

	@Autowired
	private SlotServiceImpl slotService;

	@MockBean
	private SlotDaoImpl slotRepository;

	/**
	 * Test scenario : available slot exists 
	 * result : booking done
	 */
	@Test
	public void slotBookingSuccessTest() {
		Slot slot = new Slot(VEHICLE_TYPE, true, null, null);
		LocalDateTime now = LocalDateTime.now();
		Slot UpdatedSlot = new Slot(VEHICLE_TYPE, false, VEHICLE_REGISTRATION, now);

		Mockito.when(slotRepository.findAvailableSlot(Mockito.anyString())).thenReturn(slot);
		Mockito.when(slotRepository.addVehicle(slot, VEHICLE_REGISTRATION)).thenReturn(UpdatedSlot);

		Slot actualSlot = slotService.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION);
		assertNotNull(actualSlot);
		assertEquals(false, actualSlot.isAvailable());
		assertEquals(VEHICLE_REGISTRATION, actualSlot.getVehicleRegistration());
		assertEquals(now, actualSlot.getWhenOccupied());
	}

	/**
	 * Test scenario : No available slot 
	 * result : no booking done
	 */
	@Test
	public void slotBookingFailureTest() {
		Mockito.when(slotRepository.findAvailableSlot(Mockito.anyString())).thenReturn(null);
		Slot actualSlot = slotService.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION);
		assertNull(actualSlot);
	}

	/**
	 * Test scenario : Slot release done 
	 * result : Bill issued
	 */
	@Test
	public void slotFreeSuccessTest() {
		LocalDateTime nowMinusTwoMinutes = LocalDateTime.now().minusMinutes(2);
		Slot slot = new Slot(VEHICLE_TYPE, true, VEHICLE_REGISTRATION, nowMinusTwoMinutes);
		Bill excpectedBill = new Bill(new BigDecimal(2), LocalDateTime.now().minusMinutes(2), LocalDateTime.now(), VEHICLE_REGISTRATION);

		Mockito.when(slotRepository.getAssociatedSlotToVehicle(VEHICLE_REGISTRATION)).thenReturn((slot))
		;
		Bill actualBill = slotService.manageCarOut(VEHICLE_REGISTRATION);

		assertNotNull(actualBill);
		assertEquals(excpectedBill.getAmount(), actualBill.getAmount());
		assertEquals(excpectedBill.getVehicleRegistration(), actualBill.getVehicleRegistration());
	}

	/**
	 * Test scenario : Slot release not done 
	 * result : No bill issued
	 */
	@Test
	public void slotFreeFailureTest() {
		Mockito.when(slotRepository.getAssociatedSlotToVehicle(VEHICLE_REGISTRATION)).thenReturn(null);
		Bill actualBill = slotService.manageCarOut(VEHICLE_REGISTRATION);
		assertNull(actualBill);
	}
}
