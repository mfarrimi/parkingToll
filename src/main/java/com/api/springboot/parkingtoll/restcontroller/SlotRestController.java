package com.api.springboot.parkingtoll.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.springboot.parkingtoll.entity.Bill;
import com.api.springboot.parkingtoll.entity.Slot;
import com.api.springboot.parkingtoll.exceptionhandler.VehicleEnterException;
import com.api.springboot.parkingtoll.exceptionhandler.VehicleExitException;
import com.api.springboot.parkingtoll.service.SlotService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class SlotRestController {
	
	@Autowired
	private SlotService slotService;

	
	@ApiOperation(value = "Manage slot booking if available according to Vehicle type")
	@PostMapping("/in/{vehicleType}/{vehicleRegistration}")
	public ResponseEntity<Slot> manageCarIn(@PathVariable String vehicleType, @PathVariable String vehicleRegistration) {
		Slot slot = slotService.manageCarIn(vehicleType, vehicleRegistration);
		
		if (slot == null) {
			throw new VehicleEnterException(slotService.getErrorMsg() + vehicleRegistration);
		}
         
        return ResponseEntity.status(HttpStatus.OK).body(slot);
	}
	
	
	@ApiOperation(value = "Manage Vehicle exit and issue a Bill ")
	@GetMapping("/out/{vehicleRegistration}")
	public ResponseEntity<Bill> manageCarOut(@PathVariable String vehicleRegistration) {
		Bill bill = slotService.manageCarOut(vehicleRegistration);
		if (bill == null) {
			throw new VehicleExitException(slotService.getErrorMsg() + vehicleRegistration);
		}
		return ResponseEntity.status(HttpStatus.OK).body(bill);
	}
	
}