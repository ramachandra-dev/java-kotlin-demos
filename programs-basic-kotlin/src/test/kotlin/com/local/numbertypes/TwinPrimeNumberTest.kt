package com.local.numbertypes

import org.junit.jupiter.api.Test

class TwinPrimeNumberTest : AbstractNumberTest() {
	@Test
	fun testTwinPrimeNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.TWIN_PRIME_NUMBER, "twinprimenumbers.txt")
	}

	@Test
	fun testTwinPrimeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.TWIN_PRIME_NUMBER, java.util.List.of<Int>(1, 3, 5, 7), 2)
	}
}