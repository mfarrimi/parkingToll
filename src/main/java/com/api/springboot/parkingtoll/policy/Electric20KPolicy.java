package com.api.springboot.parkingtoll.policy;

import java.math.BigDecimal;

/**
 * Class to implement billing Policy for 20k Vehicles 
 * @author 
 *
 */
public class Electric20KPolicy implements Policy {

	@Override
	public BigDecimal calculateAmount(long durationInMinutes) {
		// Billing Policy each minute costs 2 euros
		return new BigDecimal(durationInMinutes * 2);
	}
}
