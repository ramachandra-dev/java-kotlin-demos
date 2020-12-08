package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class EvilNumber : INumberTypeOperation {
	// An Evil number is a positive whole number that has an even number of 1�s in
	// its binary equivalent.
	// The binary equivalent of 9 is 1001, and 1001 contains even numbers of 1�s
	// 3,5,6,9,10,12,15,17,18
	override fun execute(input: Long, order: Long): Boolean {
		var binary = toBinary(input)
		var count = 0
		while (binary != 0L) {
			if (binary % 10 == 1L) {
				count++
			}
			binary /= 10
		}
		val status = (count % 2 == 0)
		log(status, input)
		return status
	}

	private fun log(status: Boolean, input: Long) {
		if (status) {
			logger.debug("An Evil Number {}", input)
		} else {
			logger.debug("Not an Evil Number {}", input)
		}
	}

	companion object {
		private fun toBinary(input: Long): Long {
			var number = input
			val binaryNumber = StringBuilder()
			while (number > 0) {
				binaryNumber.append(number % 2)
				number /= 2
			}
			return (binaryNumber.toString()).toLong()
		}
	}
}