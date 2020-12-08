package com.local.numbertypes
import org.junit.jupiter.api.Test
class StrongNumberTest : AbstractNumberTest() {
	@Test
	fun testStrongNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.STRONG_NUMBER, "strongnumbers.txt")
	}

	@Test
	fun testStrongNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.STRONG_NUMBER, java.util.List.of<Int>(1, 2, 145), 3)
	}
}