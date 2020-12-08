package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class BuzzNumber : INumberTypeOperation {
	// A number is called a buzz number if it is divisible by 7 or it ends with 7
	// 7,14,17,21,27,28,35,37,42,47,49,56,57,63,67,70,77,84,87,91,97,98,105,107
	override fun execute(input: Long, order: Long): Boolean {
		if (input % 10 == 7L || input % 7 == 0L) {
			logger.debug("An Buzz Number {}", input)
			return true
		}
		logger.debug("Not an Buzz Number {}", input)
		return false
	}
}