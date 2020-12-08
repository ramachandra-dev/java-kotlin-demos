package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class SpyNumber : INumberTypeOperation {
	// A number whose sum of the digits of is equal to the product of its digits
	// 132
	// The sum of digits = 1 + 3 + 2 = 6
	// The product of the digits = 1 * 3 * 2 = 6
	fun factorial(number: Long): Long {
		return if (number == 1L || number == 0L) 1 else number * factorial(number - 1)
	}

	override fun execute(input: Long, order: Long): Boolean {
		var sum = 0L
		var product = 1L
		var number = input
		while (number != 0L) {
			val lastDigit = number % 10
			sum += lastDigit
			product *= lastDigit
			number /= 10
		}
		val status = sum == product
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Spy Number {}", input)
		} else {
			logger.debug("Not an Spy Number {}", input)
		}
	}
}