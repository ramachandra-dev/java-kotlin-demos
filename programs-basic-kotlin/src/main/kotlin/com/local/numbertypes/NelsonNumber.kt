package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class NelsonNumber : INumberTypeOperation {
	// The number 111 is sometimes called a Nelson. Multiples of 111 are called a
	// double Nelson (222), triple Nelson (333)
	// 111,222,333,444,555,666,777
	override fun execute(input: Long, order: Long): Boolean {
		val status = input % 111 == 0L
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Nelson Number {}", input)
		} else {
			logger.debug("Not an Nelson Number {}", input)
		}
	}
}