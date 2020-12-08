package com.local.numbertypes

import org.junit.jupiter.api.Test

class DuckNumberTest : AbstractNumberTest() {
	@Test
	fun testDuckNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.DUCK_NUMBER, "ducknumbers.txt")
	}

	@Test
	fun testDuckNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.DUCK_NUMBER, java.util.List.of<Int>(10, 20, 30, 40, 50, 60, 70, 80, 299593), 8)
	}
}