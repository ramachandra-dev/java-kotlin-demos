package com.local.numbertypes

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
class NivenNumber : INumberTypeOperation {
	// A given number base is an integer that is divisible by the sum of its digits
	//  1,2,3,4,5,6,7,8,9,10,12,18,20,21,24,27,30
	// 20%(2+0), 21%(2+1)
	override fun execute(input: Long, order: Long): Boolean {
		if (input == 0L) {
			return false
		}
		val sum = sumOfInput(input)
		if (sum == 0L) {
			return false
		}
		val status = input % sum == 0L
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Harshad Number {}", input)
		} else {
			logger.debug("Not an Harshad Number {}", input)
		}
	}

	private fun sumOfInput(input: Long): Long {
		var number = input
		var sum = 0
		if (number == 0L) {
			return sum.toLong()
		}
		while (number != 0L) {
			sum += (number % 10).toInt();
			number /= 10
		}
		return sum.toLong()
	}
}