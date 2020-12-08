package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class MagicNumber : INumberTypeOperation {
	// A number is said to be a magic number if the sum of its digits is calculated
	// till a single digit recursively by adding the sum of the digits after every
	// addition. If the single-digit comes out to be 1, then the number is a magic
	// number
	// 1,10,19,28,37,46,55,64,73,82,91
	// 1+9 = 10 --> 1+0 --> 1
	fun factorial(number: Long): Long {
		return if (number == 1L || number == 0L) 1 else number * factorial(number - 1)
	}

	override fun execute(input: Long, order: Long): Boolean {
		var number = input
		while (number / 10 != 0L) {
			number = sumOfDigits(number)
		}
		val status = number == 1L
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Magic Number {}", input)
		} else {
			logger.debug("Not an Magic Number {}", input)
		}
	}

	companion object {
		fun sumOfDigits(input: Long): Long {
			var number = input
			var sum = 0
			while (number != 0L) {
				sum += (number % 10).toInt()
				number /= 10
			}
			return sum.toLong()
		}
	}
}