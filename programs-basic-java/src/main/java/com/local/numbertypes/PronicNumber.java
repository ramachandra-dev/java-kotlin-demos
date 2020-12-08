package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PronicNumber implements INumberTypeOperation {
	// A pronic number is a number which is the product of two consecutive integers,
	// that is, a number of the form n(n + 1)
	// 2,6,12,20,30,42
	// 5 * (5*1) = 30

	@Override
	public boolean execute(long input, long order) {
		// loop until square root of the number
		for (var index = 0; index <= (int) Math.sqrt(input); index++) {
			if (input == index * (index + 1)) {
				log.debug("An Pronic Number {}, {} * ({} + 1)", input, index, index);
				return true;
			}
		}
		log.debug("Not an Pronic Number {}", input);
		return false;
	}
}