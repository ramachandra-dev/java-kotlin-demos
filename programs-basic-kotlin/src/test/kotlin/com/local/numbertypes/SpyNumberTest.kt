package com.local.numbertypes
import org.junit.jupiter.api.Test
class SpyNumberTest : AbstractNumberTest() {
	@Test
	fun testSpyNumbers() {
		testNumbersTypeByFile(com.local.numbertypes.NumberType.SPY_NUMBER, "spynumbers.txt")
	}

	@Test
	fun testSpyNumbersByList() {
		// Pass the order as 5
		testNumbersTypeByList(com.local.numbertypes.NumberType.SPY_NUMBER, java.util.List.of<Int>(132, 1123, 123123), 1)
	}
}