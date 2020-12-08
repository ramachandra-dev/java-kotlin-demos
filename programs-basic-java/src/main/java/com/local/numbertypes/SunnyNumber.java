package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.sqrt;

@Slf4j
public class SunnyNumber implements INumberTypeOperation {
	// A number N is called sunny number if the square root of the number N+1 is an
	// integer number.
	// 24+1 = 25, 24 is a sunny number
	// 3,8,15,24,35,48,63,80,99

	@Override
	public boolean execute(long input, long order) {
		if (sqrt(input + 1f) % 1 == 0) {
			log.debug("An Sunny Number {}", input);
			return true;
		}
		log.debug("Not a Sunny Number {}", input);
		return false;
	}

}