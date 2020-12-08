package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class MultiNivenNumber : INumberTypeOperation {
	// A given number base is an integer that is divisible by the sum of its digits
	// When a Niven number is divided by the sum of digits and produces another
	// Niven number
	// then the number is called multiple Niven number
	// 21
	override fun execute(input: Long, order: Long): Boolean {
		if (!NumberType.NIVEN_NUMBER.getOperation().execute(input, order)) {
			return false
		}
		val sum = sumOfInput(input)
		if (sum == 0L) {
			return false
		}
		val status: Boolean = NumberType.NIVEN_NUMBER.getOperation().execute(input / sum, order)
		return if (status) {
			logger.debug("An Multiple Harshad Number {}", input)
			true
		} else {
			logger.debug("Not An Multiple Harshad Number {}", input)
			false
		}
	}

	private fun sumOfInput(input: Long): Long {
		var number = input
		var sum = 0
		if (number == 0L) {
			return sum.toLong()
		}
		while (number != 0L) {
			sum += (number % 10).toInt()
			number /= 10
		}
		return sum.toLong()
	}
}