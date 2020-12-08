package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TechNumber implements INumberTypeOperation {
	// A number which has an even number of digits, and when the number of digits
	// split into two halves then the square of the sum of those halves is equal to
	// the same number
	// 81,2025,3025
	// Split 2025 into two halves, then
	// first half = 20
	// second half = 25
	// sum of the halves = 20+25 = 45
	// square of the sum of the halves = 45*45 = 2025

	long factorial(long number) {
		return (number == 1l || number == 0l) ? 1 : number * factorial(number - 1);
	}

	@Override
	public boolean execute(long input, long order) {
		var number = input;
		var count = 0;

		// count number of digits
		for (; number != 0; number /= 10) {
			count++;
		}

		if (count % 2 != 0) {
			return false;
		}

		var firstHalf = input / (int) Math.pow(10, count / 2f);
		var lastHalf = input % (int) Math.pow(10, count / 2f);

		var sum = firstHalf + lastHalf;

		if (sum * sum == input) {
			log.debug("An Tech Number {}", input);
			return true;
		}
		log.debug("Not a Tech Number {}", input);
		return false;
	}

}