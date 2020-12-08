package com.local.numbertypes;

import mu.KotlinLogging
import kotlin.math.abs

private val logger = KotlinLogging.logger {}
class TwinPrimeNumber : INumberTypeOperation {
	// A pair of prime numbers having a difference of 2 is called a twin prime
	// number
	// (3,5)
	fun factorial(number: Long): Long {
		return if (number == 1L || number == 0L) 1 else number * factorial(number - 1)
	}

	override fun execute(input: Long, order: Long): Boolean {
		if (abs(order - input) != 2L) {
			logger.debug("Not Twin Primes {}, {}", input, order)
			return false
		}
		if (NumberType.PRIME_NUMBER.getOperation().execute(input, 0)
				&& NumberType.PRIME_NUMBER.getOperation().execute(order, 0)) {
			logger.debug("Twin Primes {}, {}", input, order)
			return true
		}
		logger.debug("Not Twin Primes {}, {}", input, order)
		return false
	}
}