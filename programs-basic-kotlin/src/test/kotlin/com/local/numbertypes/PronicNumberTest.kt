package com.local.numbertypes
import org.junit.jupiter.api.Test
class PronicNumberTest : AbstractNumberTest() {
	@Test
	fun testPronicNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.PRONIC_NUMBER, "pronicnumbers.txt")
	}

	@Test
	fun testPronicNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.PRONIC_NUMBER, java.util.List.of<Int>(2, 6, 12, 20, 30, 42), 6)
	}
}