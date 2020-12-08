package com.local.numbertypes

import mu.KotlinLogging
import kotlin.math.pow

private val logger = KotlinLogging.logger {}
class ArmstrongNumber : INumberTypeOperation {
	// A positive integer is called Armstrong number of order n if, abcd = a^n + b^n
	// + c^n + d^n
	// 1,4150,4151
	// 4^5 + 1^5 + 5^5 + 1^5 = 1024 + 1 + 3125 + 1 = 4151
	override fun execute(input: Long, order: Long): Boolean {
		var sum = 0L
		if (input == 0L) {
			return false
		}
		var number = input
		while (number != 0L) {
			val lastDigit = number % 10
			sum += lastDigit.toDouble()
					.pow(order.toDouble())
					.toLong()
			number /= 10
		}
		val status = (input == sum)
		log(status, input)
		return status
	}

	private fun log(status: Boolean, number: Long) {
		if (status) {
			logger.debug("An Armstrong Number {}", number)
		} else {
			logger.debug("Not an Armstrong Number {}", number)
		}
	}
}