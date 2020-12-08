package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EvilNumber implements INumberTypeOperation {
	// An Evil number is a positive whole number that has an even number of 1s in
	// its binary equivalent.
	// The binary equivalent of 9 is 1001, and 1001 contains even numbers of 1s
	// 3,5,6,9,10,12,15,17,18

	@Override
	public boolean execute(long input, long order) {
		long binary = toBinary(input);
		var count = 0;
		for (; binary != 0; binary /= 10) {
			if (binary % 10 == 1) {
				count++;
			}
		}
		var status = (count % 2 == 0);
		log(status, input);
		return status;
	}

	private static long toBinary(long input) {
		var binaryNumber = new StringBuilder();
		for (; input > 0; input /= 2) {
			binaryNumber.append(input % 2);
		}
		return Long.valueOf(binaryNumber.toString());
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Evil Number {}", input);
		} else {
			log.debug("Not an Evil Number {}", input);
		}
	}
}