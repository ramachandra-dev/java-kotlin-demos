package com.local.numbertypes
import org.junit.jupiter.api.Test
class NeonNumberTest : AbstractNumberTest() {
	@Test
	fun testNeonNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.NEON_NUMBER, "neonnumbers.txt")
	}

	@Test
	fun testNeonNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.NEON_NUMBER, java.util.List.of<Int>(1, 9), 2)
	}
}