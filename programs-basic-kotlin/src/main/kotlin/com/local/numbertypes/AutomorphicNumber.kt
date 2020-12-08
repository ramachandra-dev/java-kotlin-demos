package com.local.numbertypes

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class AutomorphicNumber : INumberTypeOperation {
	// If the square of the number ends with the same number.
	// 1,5,6,25,76,376,625
	// 5*5 = 25
	// 25*25 = 225
	override fun execute(input: Long, order: Long): Boolean {
		var number = input
		var square = number * number
		while (number != 0L) {
			if (number % 10 != square % 10) {
				logger.debug("Not an Automorphic Number {}", input)
				return false
			}
			number /= 10
			square /= 10
		}
		logger.info("An Automorphic Number {}", input)
		return true
	}
}