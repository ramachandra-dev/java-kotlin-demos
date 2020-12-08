package com.local.numbertypes

import mu.KotlinLogging
import kotlin.math.pow
import kotlin.math.sqrt

private val logger = KotlinLogging.logger {}
open class PerfectNumber : INumberTypeOperation {
	// A number whose factors sum except itself, is equal to the same number
	// 1,6,28,496
	// Factors of 28 are 1, 2, 4, 7, 14
	// The sum of factors of 28 = 1+2+4+7+14 = 28
	override fun execute(input: Long, order: Long): Boolean {
		var sumOfFactors = 0
		var counter = 1
		while (counter <= sqrt(input.toDouble())) {
			if (input % counter == 0L) {
				sumOfFactors += counter
				if (counter!= 1 && (input / counter).toDouble() != sqrt(input.toDouble())) {
					sumOfFactors += (input / counter).toInt()
				}
			}
			counter++
		}
		val status = sumOfFactors.toLong() == input
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Perfect Number {}", input)
		} else {
			logger.debug("Not an Perfect Number {}", input)
		}
	}

	protected fun caculuateSum(primeNumber: Int, powerFactor: Int): Long {
		return (primeNumber.toDouble().pow(powerFactor + 1.toDouble()) - 1).toLong()/ (primeNumber - 1)
	}
}