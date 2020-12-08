package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class NeonNumber : INumberTypeOperation {
	// If the sum of digits of the square of the number is equal to the same number
	// then the number is called Neon number
	// 1,9
	override fun execute(input: Long, order: Long): Boolean {
		var sum = 0L
		var square = input * input
		while (square != 0L) {
			sum += square % 10
			square /= 10
		}
		val status = sum == input
		log(status, input)
		return sum == input
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Neon Number {}", input)
		} else {
			logger.debug("Not an Neon Number {}", input)
		}
	}
}