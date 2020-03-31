package com.api.springboot.parkingtoll.restcontroller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.api.springboot.parkingtoll.entity.Bill;
import com.api.springboot.parkingtoll.entity.Slot;
import com.api.springboot.parkingtoll.exceptionhandler.VehicleEnterException;
import com.api.springboot.parkingtoll.exceptionhandler.VehicleExitException;
import com.api.springboot.parkingtoll.service.SlotService;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class SlotRestControllerTest {

	private static final LocalDateTime TIME = LocalDateTime.now().minusMinutes(1);
	private static final String VEHICLE_TYPE = "GAS";
	private static final String VEHICLE_REGISTRATION = "AB-00-CD";

	@Autowired
	private SlotRestController slotRestController;

	@MockBean
	private SlotService slotService;
	
	/**
	 * Test scenario : manage Vehicle enter success
	 * result : slot  booked
	 */
	@Test
	public void vehicleEnterSuccess() {
		Slot excpectedSlot = new Slot(VEHICLE_TYPE, false, VEHICLE_REGISTRATION, TIME);
		Mockito.when(slotService.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION)).thenReturn(excpectedSlot);

		ResponseEntity<Slot> actualSlot = slotRestController.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION);
		assertNotNull(actualSlot);
		assertNotNull(actualSlot.getBody());
		assertEquals(200, actualSlot.getStatusCodeValue());
		assertEquals(excpectedSlot.getVehicleType(), actualSlot.getBody().getVehicleType());
		assertEquals(excpectedSlot.getVehicleRegistration(), actualSlot.getBody().getVehicleRegistration());
		assertEquals(excpectedSlot.isAvailable(), actualSlot.getBody().isAvailable());
		assertEquals(excpectedSlot.getWhenOccupied(), actualSlot.getBody().getWhenOccupied());
	}
	
	/**
	 * Test scenario : manage Vehicle enter Failure
	 * result : slot not booked
	 */
	@Test(expected = VehicleEnterException.class)
	public void vehicleEnterFailure() {
		Mockito.when(slotService.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION)).thenReturn(null);
		slotRestController.manageCarIn(VEHICLE_TYPE, VEHICLE_REGISTRATION);
	}
	
	/**
	 * Test scenario : manage Vehicle exit success
	 * result : bill  issued
	 */
	@Test
	public void vehicleExitSuccess() {
		Bill excpectedBill = new Bill(new BigDecimal(10), TIME, LocalDateTime.now(),
				VEHICLE_REGISTRATION);
		Mockito.when(slotService.manageCarOut(VEHICLE_REGISTRATION)).thenReturn(excpectedBill);

		ResponseEntity<Bill> actualBill = slotRestController.manageCarOut(VEHICLE_REGISTRATION);
		assertNotNull(actualBill);
		assertNotNull(actualBill.getBody());
		assertEquals(200, actualBill.getStatusCodeValue());
		assertEquals(excpectedBill.getAmount(), actualBill.getBody().getAmount());
		assertEquals(excpectedBill.getVehicleRegistration(), actualBill.getBody().getVehicleRegistration());
	}
	
	/**
	 * Test scenario : manage Vehicle exit Failure
	 * result : bill  not issued
	 */
	@Test(expected = VehicleExitException.class)
	public void vehicleExitFailure() {
		Mockito.when(slotService.manageCarOut(VEHICLE_REGISTRATION)).thenReturn(null);
		slotRestController.manageCarOut(VEHICLE_REGISTRATION);
		
	}
}
