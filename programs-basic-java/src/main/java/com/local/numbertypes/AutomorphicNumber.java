package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutomorphicNumber implements INumberTypeOperation {
	// If the square of the number ends with the same number.
	// 1,5,6,25,76,376,625
	// 5*5 = 25
	// 25*25 = 225

	@Override
	public boolean execute(long input, long order) {
		var number = input;
		for (var square = number * number; number != 0; number /= 10, square /= 10) {
			if ((number % 10) != (square % 10)) {
				log.debug("Not an Automorphic Number {}", input);
				return false;
			}
		}
		log.info("An Automorphic Number {}", input);
		return true;
	}

}