package com.local.numbertypes;

import static java.lang.Math.pow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DisariumNumber implements INumberTypeOperation {
	// Sum of its digits powered with their respective position is equal to the
	// original number
	// 1,2,3,4,5,6,7,8,9,89,135,175,518,598,1306,1676,2427
	// 8^1 + 9^2 = 89
	// 1^1 + 3^2 + 5^3 = 135
	// 1^1 + 7^2 + 5^3 = 175
	// 5^1 + 1^2 + 8^3 = 518
	// 1^1 + 3^2 + 0^3 + 6^4 = 1306

	@Override
	public boolean execute(long input, long order) {
		var sum = 0l;
		var digits = countDigits(input);
		var number = input;
		if (number == 0) {
			return false;
		}
		while (number != 0) {
			var lastDigit = number % 10;
			sum += (long) pow(lastDigit, digits);
			number /= 10;
			digits--;
		}
		var status = sum == input;
		log(status, input);
		return status;
	}

	private long countDigits(long number) {
		long totalDigits = 0;
		for (; number != 0; number /= 10) {
			totalDigits++;
		}
		return totalDigits;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Disarium Number {}", input);
		} else {
			log.debug("Not an Disarium Number {}", input);
		}
	}
}