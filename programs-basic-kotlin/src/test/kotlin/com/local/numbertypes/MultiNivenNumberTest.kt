package com.local.numbertypes
import org.junit.jupiter.api.Test
class MultiNivenNumberTest : AbstractNumberTest() {
	@Test
	fun testMultiNivenNumbers() {
		testNumbersTypeByFile(NumberType.MULTI_NIVEN_NUMBER, "multinivennumbers.txt")
	}

	@Test
	fun testMultiNivenNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(NumberType.MULTI_NIVEN_NUMBER, listOf(1, 4150, 4151) , 1)
	}
}