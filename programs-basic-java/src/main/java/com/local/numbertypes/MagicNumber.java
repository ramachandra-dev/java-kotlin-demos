package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MagicNumber implements INumberTypeOperation {
	// A number is said to be a magic number if the sum of its digits is calculated
	// till a single digit recursively by adding the sum of the digits after every
	// addition. If the single-digit comes out to be 1, then the number is a magic
	// number
	// 1,10,19,28,37,46,55,64,73,82,91
	// 1+9 = 10 --> 1+0 --> 1

	long factorial(long number) {
		return (number == 1l || number == 0l) ? 1 : number * factorial(number - 1);
	}

	@Override
	public boolean execute(long input, long order) {
		var number = input;
		while (number / 10 != 0) {
			number = sumOfDigits(number);
		}
		var status = (number == 1);
		log(status, input);
		return status;
	}

	public static long sumOfDigits(long number) {
		var sum = 0;
		for (; number != 0; number /= 10) {
			sum += number % 10;
		}
		return sum;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Magic Number {}", input);
		} else {
			log.debug("Not an Magic Number {}", input);
		}
	}

}