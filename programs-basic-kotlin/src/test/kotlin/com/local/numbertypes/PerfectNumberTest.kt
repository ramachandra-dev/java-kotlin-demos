package com.local.numbertypes
import org.junit.jupiter.api.Test
class PerfectNumberTest : AbstractNumberTest() {
	@Test
	fun testPerfectNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.PERFECT_NUMBER, "perfectnumbers.txt")
	}

	@Test
	fun testPerfectNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.PERFECT_NUMBER, java.util.List.of<Int>(1, 6, 28, 496), 4)
	}
}