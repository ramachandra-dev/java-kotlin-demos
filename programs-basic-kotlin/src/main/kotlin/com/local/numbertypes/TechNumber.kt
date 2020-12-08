package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class TechNumber : INumberTypeOperation {
	// A number which has an even number of digits, and when the number of digits
	// split into two halves then the square of the sum of those halves is equal to
	// the same number
	// 81,2025,3025
	// Split 2025 into two halves, then
	// first half = 20
	// second half = 25
	// sum of the halves = 20+25 = 45
	// square of the sum of the halves = 45*45 = 2025
	fun factorial(number: Long): Long {
		return if (number == 1L || number == 0L) 1 else number * factorial(number - 1)
	}

	override fun execute(input: Long, order: Long): Boolean {
		var number = input
		var count = 0

		// count number of digits
		while (number != 0L) {
			count++
			number /= 10
		}
		if (count % 2 != 0) {
			return false
		}
		val firstHalf = (input / java.lang.Math.pow(10.0, count / 2.toDouble())).toLong()
		val lastHalf = (input % java.lang.Math.pow(10.0, count / 2.toDouble())).toLong()
		val sum = firstHalf + lastHalf
		if (sum * sum == input) {
			logger.debug("An Tech Number {}", input)
			return true
		}
		logger.debug("Not a Tech Number {}", input)
		return false
	}
}