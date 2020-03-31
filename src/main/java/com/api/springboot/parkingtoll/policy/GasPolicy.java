package com.api.springboot.parkingtoll.policy;

import java.math.BigDecimal;

/**
 * Class to implement billing Policy for GAS Vehicles 
 * @author 
 *
 */
public class GasPolicy implements Policy {

	@Override
	public BigDecimal calculateAmount(long durationInMinutes) {
		// Billing Policy each minute costs 1 euros
		return new BigDecimal(durationInMinutes);
	}

}
