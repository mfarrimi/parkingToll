package com.api.springboot.parkingtoll.policy;

import java.math.BigDecimal;

/**
 * Class to implement billing Policy for 50K Vehicles 
 * @author 
 *
 */
public class Electric50KPolicy implements Policy {

	@Override
	public BigDecimal calculateAmount(long durationInMinutes) {
		// Billing Policy each minute costs 3 euros
		return new BigDecimal(durationInMinutes * 3);
	}
}