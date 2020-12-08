package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NivenNumber implements INumberTypeOperation {
	// A given number base is an integer that is divisible by the sum of its digits
	//  1,2,3,4,5,6,7,8,9,10,12,18,20,21,24,27,30
	// 20%(2+0), 21%(2+1)

	@Override
	public boolean execute(long input, long order) {
		if (input == 0) {
			return false;
		}
		var sum = sumOfInput(input);
		if (sum == 0) {
			return false;
		}
		var status = (input % sum == 0);
		log(status, input);
		return status;
	}

	private void log(boolean status, long input) {
		if (status) {
			log.debug("An Niven Number {}", input);
		} else {
			log.debug("Not an Niven Number {}", input);
		}
	}

	private long sumOfInput(long input) {
		var sum = 0;
		if (input == 0) {
			return sum;
		}
		for (; input != 0; input /= 10) {
			sum += input % 10;
		}
		return sum;

	}

}