package com.local.numbertypes

import mu.KotlinLogging
private val logger = KotlinLogging.logger {}
class PrimeNumber : INumberTypeOperation {
	override fun execute(input: Long, order: Long): Boolean {
		if (input <= 1) {
			logger.debug(NOT_PRIME, input)
			return false
		}
		if (input <= 3) {
			logger.debug(PRIME, input)
			return true
		}
		if (input % 2 == 0L || input % 3 == 0L) {
			logger.debug(NOT_PRIME, input)
			return false
		}

		// logic for remaining numbers
		/**
		 *
		 * The simplest primality test is trial division: given an input number, n,
		 * check whether it is evenly divisible by any prime number between 2 and √n
		 * (i.e. that the division leaves no remainder). If so, then n is composite.
		 * Otherwise, it is prime
		 *
		 * All even numbers greater than 2 can also be eliminated since, if an even
		 * number can divide n, so can 2.
		 *
		 * Let's use trial division to test the primality of 17. We need only test for
		 * divisors up to √n, i.e. integers less than or equal to 17 ≈ 4.12 , namely 2,
		 * 3, and 4. We can skip 4 because it is an even number: if 4 could evenly
		 * divide 17, 2 would too, and 2 is already in the list. That leaves 2 and 3. We
		 * divide 17 with each of these numbers, and we find that neither divides 17
		 * evenly -- both divisions leave a remainder. So, 17 is prime.
		 *
		 * Observe that all primes greater than 3 are of the form 6k ± 1, where k is any
		 * integer greater than 0. This is because all integers can be expressed as (6k
		 * + i), where i = −1, 0, 1, 2, 3, or 4. Note that 2 divides (6k + 0), (6k + 2),
		 * and (6k + 4) and 3 divides (6k + 3). So, a more efficient method is to test
		 * whether n is divisible by 2 or 3, then to check through all numbers of the
		 * form 6 k ± 1 ≤ √n . This is 3 times faster than testing all numbers up to √n.
		 *
		 * all primes greater than c# (c primorial) are of the form c# · k + i, for i <
		 * c#, where c and k are integers and i represents the numbers that are coprime
		 * to c#. For example, let c = 6. Then c# = 2 · 3 · 5 = 30. All integers are of
		 * the form 30k + i for i = 0, 1, 2,...,29 and k an integer. However, 2 divides
		 * 0, 2, 4,..., 28; 3 divides 0, 3, 6,..., 27; and 5 divides 0, 5, 10,..., 25.
		 * So all prime numbers greater than 30 are of the form 30k + i for i = 1, 7,
		 * 11, 13, 17, 19, 23, 29 (i.e. for i < 30 such that gcd(i,30) = 1). Note that
		 * if i and 30 were not coprime, then 30k + i would be divisible by a prime
		 * divisor of 30, namely 2, 3 or 5, and would therefore not be prime. (Note: Not
		 * all numbers which meet the above conditions are prime. For example: 437 is in
		 * the form of c#k+i for c#(7)=210,k=2,i=17. However, 437 is a composite number
		 * equal to 19*23).
		 */
		var i = 5
		while (i <= java.lang.Math.sqrt(input.toDouble())) {

			// 6k+1 => number%i
			// 6k-1 => number % (i+2)
			if (input % i == 0L || input % (i + 2) == 0L) {
				logger.debug(NOT_PRIME, input)
				return false
			}
			i += 6
		}
		logger.debug(PRIME, input)
		return true
	}

	companion object {
		// A natural number which has only two factors ( 1 and itself )
		// 2,3,5,7,11,13,17,19,23,29,31
		private const val NOT_PRIME = "Not a Prime Number {}"
		private const val PRIME = "An Prime Number {}"
	}
}