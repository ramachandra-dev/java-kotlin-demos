package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MultiNivenNumber implements INumberTypeOperation {
	// A given number base is an integer that is divisible by the sum of its digits

	// When a Niven number is divided by the sum of digits and produces another
	// Niven number
	// then the number is called multiple Niven number
	// 21

	@Override
	public boolean execute(long input, long order) {
		if (!NumberType.NIVEN_NUMBER.getOperation().execute(input, order)) {
			return false;
		}

		var sum = sumOfInput(input);

		if (sum == 0) {
			return false;
		}

		var status = NumberType.NIVEN_NUMBER.getOperation().execute(input / sum, order);
		if (status) {
			log.debug("An Multiple Niven Number {}", input);
			return true;
		} else {
			log.debug("Not An Multiple Niven Number {}", input);
			return false;
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