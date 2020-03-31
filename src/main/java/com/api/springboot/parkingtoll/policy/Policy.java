package com.api.springboot.parkingtoll.policy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import com.api.springboot.parkingtoll.entity.Bill;

public interface Policy {

	/**
	 * Create bill
	 * 
	 * @param startDate
	 * @param vehicleRegistration
	 * @return
	 */
	default Bill creatBill(LocalDateTime startDate, String vehicleRegistration) {
		LocalDateTime now = LocalDateTime.now();
		Duration duration = Duration.between(now, startDate);
		long durationInMinutes = Math.abs(duration.toMinutes());
		return new Bill(calculateAmount(durationInMinutes), startDate, now, vehicleRegistration);
	}

	/**
	 * Calculate bill amount according to Policy type
	 * 
	 * @param durationInMinutes
	 * @return
	 */
	public BigDecimal calculateAmount(long durationInMinutes);
}
