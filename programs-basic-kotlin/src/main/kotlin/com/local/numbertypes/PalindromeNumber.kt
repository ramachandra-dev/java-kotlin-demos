package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class PalindromeNumber : INumberTypeOperation {
	// If the Reverse of a number is equal to the same number then the number is
	// called palindrome number.
	// 1,2,3,4,5,6,7,8,9,11,22,33,44,55
	override fun execute(input: Long, order: Long): Boolean {
		var reverseNumber = 0L
		var number = input
		while (number != 0L) {
			reverseNumber *= 10
			reverseNumber += number % 10
			number /= 10
		}
		val status = input == reverseNumber
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Palindrome Number {}", input)
		} else {
			logger.debug("Not an Palindrome Number {}", input)
		}
	}
}