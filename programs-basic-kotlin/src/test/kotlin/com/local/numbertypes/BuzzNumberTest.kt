package com.local.numbertypes

import org.junit.jupiter.api.Test

class BuzzNumberTest : AbstractNumberTest() {
	@Test
	fun testBuzzNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.BUZZ_NUMBER, "buzznumbers.txt")
	}

	@Test
	fun testBuzzNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.BUZZ_NUMBER, java.util.List.of<Int>(7, 14, 17, 21, 27, 28, 35, 37, 42, 47, 49, 56, 57, 63, 67, 70, 77,
				84, 87, 91, 97, 98, 105, 107), 24)
	}
}