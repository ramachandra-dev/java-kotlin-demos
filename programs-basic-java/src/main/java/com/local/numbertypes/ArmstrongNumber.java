package com.local.numbertypes;

import static java.lang.Math.pow;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArmstrongNumber implements INumberTypeOperation {
	// A positive integer is called Armstrong number of order n if, abcd = a^n + b^n
	// + c^n + d^n
	// 1,4150,4151
	// 4^5 + 1^5 + 5^5 + 1^5 = 1024 + 1 + 3125 + 1 = 4151
	@Override
	public boolean execute(long input, long order) {
		var sum = 0l;
		if (input == 0) {
			return false;
		}
		var number = input;
		while (number != 0) {
			var lastDigit = number % 10;
			sum += (long) pow(lastDigit, order);
			number /= 10;
		}
		var status = (input == sum);
		log(status, input);
		return status;
	}

	private void log(boolean status, long number) {
		if (status) {
			log.debug("An Armstrong Number {}", number);
		} else {
			log.debug("Not an Armstrong Number {}", number);
		}
	}
}