package com.local.numbertypes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwinPrimeNumber implements INumberTypeOperation {
	// A pair of prime numbers having a difference of 2 is called a twin prime
	// number
	// (3,5)

	long factorial(long number) {
		return (number == 1l || number == 0l) ? 1 : number * factorial(number - 1);
	}

	@Override
	public boolean execute(long input, long input2) {
		if (Math.abs(input2 - input) != 2) {
			log.debug("Not Twin Primes {}, {}", input, input2);
			return false;
		}

		if (NumberType.PRIME_NUMBER.getOperation().execute(input, 0)
				&& NumberType.PRIME_NUMBER.getOperation().execute(input2, 0)) {
			log.debug("Twin Primes {}, {}", input, input2);
			return true;
		}
		log.debug("Not Twin Primes {}, {}", input, input2);
		return false;

	}

}