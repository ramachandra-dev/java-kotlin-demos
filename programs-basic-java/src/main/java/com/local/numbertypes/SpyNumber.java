package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpyNumber implements INumberTypeOperation {
	// A number whose sum of the digits of is equal to the product of its digits
	// 132
	// The sum of digits = 1 + 3 + 2 = 6
	// The product of the digits = 1 * 3 * 2 = 6

	long factorial(long number) {
		return (number == 1l || number == 0l) ? 1 : number * factorial(number - 1);
	}

	@Override
	public boolean execute(long input, long order) {
		var sum = 0l;
		var product = 1l;
		var number = input;
		while (number != 0) {
			var lastDigit = number % 10;
			sum = sum + lastDigit;
			product = product * lastDigit;
			number = number / 10;
		}
		var status = (sum == product);
		log(status, input);
		return status;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Spy Number {}", input);
		} else {
			log.debug("Not an Spy Number {}", input);
		}
	}

}