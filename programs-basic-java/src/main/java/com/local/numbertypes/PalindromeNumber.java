package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PalindromeNumber implements INumberTypeOperation {
	// If the Reverse of a number is equal to the same number then the number is
	// called palindrome number.
	// 1,2,3,4,5,6,7,8,9,11,22,33,44,55

	@Override
	public boolean execute(long input, long order) {
		var reverseNumber = 0l;
		var number = input;
		while (number != 0) {
			reverseNumber = reverseNumber * 10;
			reverseNumber = reverseNumber + number % 10;
			number = number / 10;
		}
		var status = (input == reverseNumber);
		log(status, input);
		return status;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Palindrome Number {}", input);
		} else {
			log.debug("Not an Palindrome Number {}", input);
		}
	}

}