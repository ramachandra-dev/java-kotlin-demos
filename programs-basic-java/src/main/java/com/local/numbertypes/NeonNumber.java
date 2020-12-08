package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NeonNumber implements INumberTypeOperation {
	// If the sum of digits of the square of the number is equal to the same number
	// then the number is called Neon number
	// 1,9

	@Override
	public boolean execute(long input, long order) {
		var sum = 0l;
		for (var square = input * input; square != 0; square /= 10) {
			sum += square % 10;
		}
		var status = (sum == input);
		log(status, input);
		return sum == input;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Neon Number {}", input);
		} else {
			log.debug("Not an Neon Number {}", input);
		}
	}
}