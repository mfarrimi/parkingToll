package com.api.springboot.parkingtoll.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Bill data model represents the bill that will be issued if vehicle exit is
 * done successfully
 * 
 * @author
 *
 */
public class Bill {
	/**
	 * The Bill amount
	 */
	private BigDecimal amount;

	/**
	 * The bill currency
	 */
	private String currency;

	/**
	 * Start parking time
	 */
	private LocalDateTime startDate;

	/**
	 * End parking time
	 */
	private LocalDateTime finishDate;

	/**
	 * Vehicle registration
	 */
	private String vehicleRegistration;

	public Bill(BigDecimal amount, LocalDateTime startDate, LocalDateTime finishDate, String vehicleRegistration) {
		this.amount = amount;
		this.currency = "EUR";
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.vehicleRegistration = vehicleRegistration;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDateTime finishDate) {
		this.finishDate = finishDate;
	}

	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	@Override
	public String toString() {
		return "Bill [amount=" + amount + ", currency=" + currency + ", startDate=" + startDate + ", finishDate="
				+ finishDate + ", vehicleRegistration=" + vehicleRegistration + "]";
	}

}
