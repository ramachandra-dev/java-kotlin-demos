package com.local.numbertypes

import mu.KotlinLogging
import java.util.function.IntFunction

private val logger = KotlinLogging.logger {}
class DuckNumber : INumberTypeOperation {
	// A number that has at least one 0 ( but not at the beginning of the number )
	// 10,20,30,40,50,60,70,80
	override fun execute(input: Long, order: Long): Boolean {
		var number = input
		while (number != 0L) {
			if (number % 10 == 0L) {
				logger.debug("An Duck Number {}", number)
				return true
			}
			number /= 10
		}
		logger.debug("Invalid Duck Number {}", input)
		return false
	}

	fun duckNumberViaString(input: Long): Boolean {
		val numberValue = input.toString()
		return if (numberValue[0] == '0') {
			false
		} else numberValue.chars()
				.mapToObj(Int::toChar)
				.skip(1)
				.anyMatch { charValue -> (charValue == '0') }
	}
}