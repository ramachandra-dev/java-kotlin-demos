package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StrongNumber implements INumberTypeOperation {
	// If the sum of the factorial of individual digits of a number is equal to the
	// same number then the number is called a strong number
	// 1, 2, 145
	// 1! + 4! + 5! = 145

	@Override
	public boolean execute(long input, long order) {
		var sum = 0l;
		if (input == 0) {
			return false;
		}
		var number = input;
		while (number != 0) {
			var lastDigit = number % 10;
			sum += factorial(lastDigit);
			number /= 10;
		}
		var status = (input == sum);
		log(status, input);
		return status;
	}

	private long factorial(long number) {
		return (number == 1l || number == 0l) ? 1 : number * factorial(number - 1);
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Strong Number {}", input);
		} else {
			log.debug("Not an Strong Number {}", input);
		}
	}

}