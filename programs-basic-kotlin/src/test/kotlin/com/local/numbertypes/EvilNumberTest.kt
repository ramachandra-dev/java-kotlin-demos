package com.local.numbertypes

import org.junit.jupiter.api.Test

class EvilNumberTest : AbstractNumberTest() {
	@Test
	fun testEvilNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.EVIL_NUMBER, "evilnumbers.txt")
	}

	@Test
	fun testEvilNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.EVIL_NUMBER, java.util.List.of<Int>(3, 5, 6, 9, 10, 12, 15, 17, 18), 9)
	}
}