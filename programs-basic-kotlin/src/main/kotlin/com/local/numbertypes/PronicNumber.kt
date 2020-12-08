package com.local.numbertypes

import mu.KotlinLogging
import kotlin.math.sqrt

private val logger = KotlinLogging.logger {}
class PronicNumber : INumberTypeOperation {
	// A pronic number is a number which is the product of two consecutive integers,
	// that is, a number of the form n(n + 1)
	// 2,6,12,20,30,42
	// 5 * (5*1) = 30
	override fun execute(input: Long, order: Long): Boolean {
		// loop until square root of the number
		for (index in 0..sqrt(input.toDouble()).toInt()) {
			if (input == index * (index + 1).toLong()) {
				logger.debug("An Pronic Number {}, {} * ({} + 1)", input, index, index)
				return true
			}
		}
		logger.debug("Not an Pronic Number {}", input)
		return false
	}
}