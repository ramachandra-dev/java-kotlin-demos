package com.local.numbertypes

import mu.KotlinLogging
import java.util.function.Predicate

private val logger = KotlinLogging.logger {}
class LongValue : Predicate<String> {
	override fun test(line: String): Boolean {
		return try {
			line.toLong();
			true;
		} catch (exception: NumberFormatException) {
			logger.debug("Long number invalid: {}", line)
			false
		}
	}
}