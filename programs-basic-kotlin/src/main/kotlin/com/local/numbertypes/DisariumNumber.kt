package com.local.numbertypes

import mu.KotlinLogging
import kotlin.math.pow

private val logger = KotlinLogging.logger {}
class DisariumNumber : INumberTypeOperation {
	// Sum of its digits powered with their respective position is equal to the
	// original number
	// 1,2,3,4,5,6,7,8,9,89,135,175,518,598,1306,1676,2427
	// 8^1 + 9^2 = 89
	// 1^1 + 3^2 + 5^3 = 135
	// 1^1 + 7^2 + 5^3 = 175
	// 5^1 + 1^2 + 8^3 = 518
	// 1^1 + 3^2 + 0^3 + 6^4 = 1306
	override fun execute(input: Long, order: Long): Boolean {
		var sum = 0L
		var digits = countDigits(input)
		var number = input
		if (number == 0L) {
			return false
		}
		while (number != 0L) {
			val lastDigit = number % 10
			sum += lastDigit.toDouble()
					.pow(digits.toDouble())
					.toLong()
			number /= 10
			digits--
		}
		val status = (sum == input)
		log(status, input)
		return status
	}

	private fun countDigits(input: Long): Long {
		var number = input
		var totalDigits: Long = 0
		while (number != 0L) {
			totalDigits++
			number /= 10
		}
		return totalDigits
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Disarium Number {}", input)
		} else {
			logger.debug("Not an Disarium Number {}", input)
		}
	}
}