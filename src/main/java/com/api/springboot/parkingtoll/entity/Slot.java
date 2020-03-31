package com.api.springboot.parkingtoll.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Data model class represents a prking slot
 * @author 
 *
 */
@Entity
@Table(name="slot")
public class Slot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="vehicle_type")
	private String vehicleType;
	
	@Column(name="is_available")
	private boolean isAvailable;
	
	@Column(name="vehicle_registration")
	private String vehicleRegistration;
	
	@Column(name="when_occupied")
	private LocalDateTime whenOccupied;


	public Slot() {}
	
	public Slot(String vehicleType, boolean isAvailable, String vehicleRegistration, LocalDateTime whenOccupied) {
		this.vehicleType = vehicleType;
		this.isAvailable = isAvailable;
		this.vehicleRegistration = vehicleRegistration;
		this.whenOccupied = whenOccupied;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	public LocalDateTime getWhenOccupied() {
		return whenOccupied;
	}

	public void setWhenOccupied(LocalDateTime whenOccupied) {
		this.whenOccupied = whenOccupied;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", vehicleType=" + vehicleType + ", isAvailable=" + isAvailable
				+ ", vehicleRegistration=" + vehicleRegistration + ", whenOccupied=" + whenOccupied + "]";
	}
}
