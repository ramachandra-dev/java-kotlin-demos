package com.local.numbertypes

import mu.KotlinLogging
import kotlin.math.sqrt

private val logger = KotlinLogging.logger {}
class SunnyNumber : INumberTypeOperation {
	// A number N is called sunny number if the square root of the number N+1 is an
	// integer number.
	// 24+1 = 25, 24 is a sunny number
	// 3,8,15,24,35,48,63,80,99
	override fun execute(input: Long, order: Long): Boolean {
		if (sqrt(input + 1.toDouble()) % 1 == 0.0) {
			logger.debug("An Sunny Number {}", input)
			return true
		}
		logger.debug("Not a Sunny Number {}", input)
		return false
	}
}