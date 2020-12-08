package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class StrongNumber : INumberTypeOperation {
	// If the sum of the factorial of individual digits of a number is equal to the
	// same number then the number is called a strong number
	// 1, 2, 145
	// 1! + 4! + 5! = 145
	override fun execute(input: Long, order: Long): Boolean {
		var sum = 0L
		if (input == 0L) {
			return false
		}
		var number = input
		while (number != 0L) {
			val lastDigit = number % 10
			sum += factorial(lastDigit)
			number /= 10
		}
		val status = input == sum
		log(status, input)
		return status
	}

	private fun factorial(number: Long): Long {
		return if (number == 1L || number == 0L) 1 else number * factorial(number - 1)
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Strong Number {}", input)
		} else {
			logger.debug("Not an Strong Number {}", input)
		}
	}
}