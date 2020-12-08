package com.local.numbertypes
import org.junit.jupiter.api.Test
class NivenNumberTest : AbstractNumberTest() {
	@Test
	fun testNivenNumbers() {
		testNumbersTypeByFile(NumberType.NIVEN_NUMBER, "nivennumbers.txt")
	}

	@Test
	fun testNivenNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(NumberType.NIVEN_NUMBER, listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 18, 20, 21, 24, 27, 30) , 17)
	}
}