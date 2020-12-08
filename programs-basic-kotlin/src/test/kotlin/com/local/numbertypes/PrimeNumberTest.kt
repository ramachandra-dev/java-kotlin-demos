package com.local.numbertypes
import org.junit.jupiter.api.Test
class PrimeNumberTest : AbstractNumberTest() {
	@Test
	fun testPrimeNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.PRIME_NUMBER, "primenumbers.txt")
	}

	@Test
	fun testPrimeNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.PRIME_NUMBER, java.util.List.of<Int>(1, 3, 5, 7, 89, 999, 12313), 4)
	}
}